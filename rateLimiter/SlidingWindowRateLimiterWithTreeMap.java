import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.concurrent.*;

public class SlidingWindowRateLimiterWithTreeMap {
    private final int maxRequests;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<String, TreeMap<Long, Integer>> clientRequestMap;
    private final ConcurrentHashMap<String, ReentrantLock> clientLocks;

    public SlidingWindowRateLimiterWithTreeMap(int maxRequests, long windowSizeSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeSeconds * 1000;
        this.clientRequestMap = new ConcurrentHashMap<>();
        this.clientLocks = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();

        // Each client gets its own TreeMap and Lock, ensuring independent processing
        clientRequestMap.putIfAbsent(clientId, new TreeMap<>());
        clientLocks.putIfAbsent(clientId, new ReentrantLock());

        ReentrantLock lock = clientLocks.get(clientId);

        // Lock only this specific client ID to prevent blocking other clients
        lock.lock();
        try {
            TreeMap<Long, Integer> timestamps = clientRequestMap.get(clientId);

            // Remove expired timestamps
            timestamps.headMap(now - windowSizeMillis, false).clear();

            // Count valid requests within the window
            int requestCount = timestamps.values().stream().mapToInt(Integer::intValue).sum();

            if (requestCount < maxRequests) {
                timestamps.put(now, timestamps.getOrDefault(now, 0) + 1);
                return true; // Request allowed
            } else {
                return false; // Request denied
            }
        } finally {
            lock.unlock(); // Release lock
        }
    }

    public static void main(String[] args) {
        SlidingWindowRateLimiterWithTreeMap rateLimiter = new SlidingWindowRateLimiterWithTreeMap(5, 10); // 5 requests per 10 seconds
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Simulating multiple clients
        String[] clientIds = {"user1", "user2", "user3"};
        
        for (String clientId : clientIds) {
            for (int i = 0; i < 7; i++) { // Each client tries 7 requests
                executor.submit(() -> {
                    boolean allowed = rateLimiter.allowRequest(clientId);
                    System.out.println(Thread.currentThread().getName() + " [" + clientId + "] -> " + 
                                       (allowed ? "Allowed" : "Blocked"));
                });
            }
        }

        executor.shutdown();
    }
}
