package uni.spring.utils;

public interface LockManager {

    public void acquire(String key);

    public void release(String key);
}
