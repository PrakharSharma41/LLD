package ScheduledExecutorService;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ExpiringCache<K, V> {
    private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    private final PriorityBlockingQueue<ExpirationEntry<K>> expiryQueue = new PriorityBlockingQueue<>();
    private final ScheduledExecutorService cleanupScheduler = Executors.newSingleThreadScheduledExecutor();
    private final ReentrantLock lock = new ReentrantLock();

    public ExpiringCache() {
        // Schedule cleanup task to run periodically
        cleanupScheduler.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.MINUTES);
    }

    // Add an entry to the cache with an expiry time
    public void put(K key, V value, long ttlInMillis) {
        long expiryTime = System.currentTimeMillis() + ttlInMillis;
        CacheEntry<V> newEntry = new CacheEntry<>(value, expiryTime);
    
        // Add to cache and update the priority queue
        cache.put(key, newEntry);
        expiryQueue.offer(new ExpirationEntry<>(key, expiryTime)); // No additional lock required
    }

    // Retrieve an entry from the cache
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null) {
            return null; // Key doesn't exist
        }
        if (System.currentTimeMillis() > entry.expiryTime) {
            cache.remove(key); // Remove expired entry
            return null;
        }
        return entry.value; // Return the value
    }

    // Remove an entry from the cache
    public void remove(K key) {
        cache.remove(key);
    }

    // Periodic cleanup to remove expired keys
    private void cleanup() {
        long now = System.currentTimeMillis();
    
        // Remove expired entries from the queue and cache
        while (!expiryQueue.isEmpty() && expiryQueue.peek().expiryTime <= now) {
            ExpirationEntry<K> expiredEntry = expiryQueue.poll(); // Thread-safe operation
            if (expiredEntry != null) {
                cache.remove(expiredEntry.key); // Remove from the cache
            }
        }
    }

    // Shutdown the scheduler
    public void shutdown() {
        cleanupScheduler.shutdown();
        try {
            if (!cleanupScheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                cleanupScheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            cleanupScheduler.shutdownNow();
        }
    }

    // Inner class to store value and expiry time
    private static class CacheEntry<V> {
        private final V value;
        private final long expiryTime;

        public CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }

    // Inner class for expiration entries
    private static class ExpirationEntry<K> implements Comparable<ExpirationEntry<K>> {
        private final K key;
        private final long expiryTime;

        public ExpirationEntry(K key, long expiryTime) {
            this.key = key;
            this.expiryTime = expiryTime;
        }

        @Override
        public int compareTo(ExpirationEntry<K> other) {
            return Long.compare(this.expiryTime, other.expiryTime);
        }
    }

    // Main method for testing with multiple threads
    public static void main(String[] args) {
        ExpiringCache<String, String> cache = new ExpiringCache<>();

        // Thread pool for simulating concurrent access
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Runnable rtr=()->{

        // };

        // Task to simulate putting data in the cache
        Runnable setTask = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                String key = threadName + "-key" + i;
                String value = threadName + "-value" + i;
                cache.put(key, value, 5000); // 5 seconds TTL
                System.out.println(threadName + " set " + key);
            }
        };

        // Submit multiple tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            executor.submit(setTask);
        }

        // Shutdown the thread pool gracefully
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the cache content
        System.out.println("Cache content after all threads finished:");
        for (Map.Entry<String, CacheEntry<String>> entry : cache.cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().value);
        }

        // Wait for 5 seconds to let entries expire
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the cache content after entries expire
        System.out.println("Cache content after expiration:");
        for (Map.Entry<String, CacheEntry<String>> entry : cache.cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().value);
        }

        // Shutdown the cleanup scheduler
        cache.shutdown();
    }
}
