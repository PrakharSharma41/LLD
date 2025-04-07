package StampedLock.optimisticRead;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

public class ConfigCache {
    private final Map<String, String> configMap = new ConcurrentHashMap<>();
    private final StampedLock stampedLock = new StampedLock();

    // Writer: Rare config updates
    public void updateConfig(String key, String value) {
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("Updating config: " + key + " -> " + value);
            configMap.put(key, value);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    // Reader: Fast optimistic reads
    public String getConfig(String key) {
        long stamp = stampedLock.tryOptimisticRead();
        String value = configMap.get(key);

        // Simulate some delay between read and validation
        try { Thread.sleep(10); } catch (InterruptedException e) {}

        if (!stampedLock.validate(stamp)) {
            // Fallback to a proper read lock
            stamp = stampedLock.readLock();
            try {
                value = configMap.get(key);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return value;
    }
}
