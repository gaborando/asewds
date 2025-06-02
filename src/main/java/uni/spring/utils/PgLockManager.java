package uni.spring.utils;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PgLockManager implements LockManager {


    private final DataSource lockDatasource;
    private Connection lockCon;

    private static final ConcurrentHashMap<String, LockWrapper> locks = new ConcurrentHashMap<>();

    public PgLockManager(DataSource lockDatasource) {
        this.lockDatasource = lockDatasource;
    }


    private static class LockWrapper {
        private final Semaphore lock = new Semaphore(1);
        private final AtomicInteger numberOfThreadsInQueue = new AtomicInteger(1);

        private LockWrapper addThreadInQueue() {
            numberOfThreadsInQueue.incrementAndGet();
            return this;
        }

        private int removeThreadFromQueue() {
            return numberOfThreadsInQueue.decrementAndGet();
        }

    }

    @SneakyThrows
    private synchronized Connection getLockConnection(){
        if(lockCon == null || !lockCon.isValid(3)){
            lockCon = lockDatasource.getConnection();
        }
        return lockCon;
    }

    public void acquire(String key) {
        if (key == null) return;
        LockWrapper lockWrapper = locks.compute(key, (k, v) -> v == null ? new LockWrapper() : v.addThreadInQueue());
        lockWrapper.lock.acquireUninterruptibly();
        try (var stmt = this.getLockConnection().prepareStatement("SELECT pg_advisory_lock(?)")) {
            stmt.setInt(1, key.hashCode());
            var resultSet = stmt.executeQuery();
            resultSet.next();
            if (resultSet.wasNull()) throw new IllegalMonitorStateException();
            var status = resultSet.getString(1);
            if (!"".equals(status)) throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void release(String key) {
        if (key == null) return;
        LockWrapper lockWrapper = locks.get(key);
        lockWrapper.lock.release();
        if (lockWrapper.removeThreadFromQueue() == 0) {
            // NB : We pass in the specific value to remove to handle the case where another thread would queue right before the removal
            locks.remove(key, lockWrapper);
        }
        try (var stmt = getLockConnection().prepareStatement("SELECT pg_advisory_unlock(?)")) {
            stmt.setInt(1, key.hashCode());
            var resultSet = stmt.executeQuery();
            resultSet.next();
            if (resultSet.wasNull()) throw new IllegalMonitorStateException();
            var status = resultSet.getBoolean(1);
            if (!status) throw new IllegalMonitorStateException("Lock key: " + key);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
