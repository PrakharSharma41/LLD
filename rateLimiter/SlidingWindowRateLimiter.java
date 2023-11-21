import java.util.ArrayDeque;
import java.util.Queue;

public class SlidingWindowRateLimiter {
    private final int windowSizeInSeconds;
    private final int maxRequests;
    private final Queue<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int windowSizeInSeconds, int maxRequests) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequests = maxRequests;
        this.requestTimestamps = new ArrayDeque<>();
    }

    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis() / 1000; // Convert to seconds

        // Remove timestamps that are outside the current time window
        while (!requestTimestamps.isEmpty() && requestTimestamps.peek() <= currentTime - windowSizeInSeconds) {
            requestTimestamps.poll();
        }
        // Check if the number of requests is within the limit
        if (requestTimestamps.size() < maxRequests) {
            // Allow the request and add the current timestamp
            requestTimestamps.offer(currentTime);
            return true;
        } else {
            // Reject the request due to rate limit exceeded
            return false;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(1, 5); // Allow 5 requests per second

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.allowRequest()) {
                System.out.println("Request " + (i + 1) + ": Allowed");
            } else {
                System.out.println("Request " + (i + 1) + ": Rate limit exceeded");
            }

            // Simulate a delay between requests
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
