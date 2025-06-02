package uni.spring.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


public class KeyLockManager implements LockManager {
    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public void acquire(String key) {
        ReentrantLock lock = lockMap.computeIfAbsent(key, k -> new ReentrantLock());
        lock.lock();
    }

    public void release(String key) {
        ReentrantLock lock = lockMap.get(key);
        if (lock != null) {
            lock.unlock();
            // Optional: cleanup if no longer used
            if (!lock.hasQueuedThreads()) {
                lockMap.remove(key, lock);
            }
        }
    }
}