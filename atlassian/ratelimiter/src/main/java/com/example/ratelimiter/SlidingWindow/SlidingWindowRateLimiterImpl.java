package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;
import java.util.HashMap;

import com.example.ratelimiter.RateLimiter;

public class SlidingWindowRateLimiterImpl implements RateLimiter{

    private final HashMap<String, SlidingWindowWithCredits> userMap = new HashMap<>();
    private final long windowSize;
    private final int maxRequests;
    private final int maxCredits;
    public SlidingWindowRateLimiterImpl(long windowSize, int maxRequests, int maxCredits) {
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
    }
    @Override
    public boolean rateLimit(String userId) {
        long currentTime = System.currentTimeMillis();
        SlidingWindowWithCredits window = userMap.get(userId);

        if (window == null) {
            window = new SlidingWindowWithCredits();
            userMap.put(userId, window);
        }

        Deque<Long> timestamps = window.getTimestamps();

        // Remove expired timestamps
        while (!timestamps.isEmpty() && currentTime - timestamps.peekFirst() > windowSize) {
            timestamps.pollFirst();
        }
        
        int currentCount = timestamps.size();
        if (currentCount < maxRequests) {
            timestamps.add(currentTime);

            // Calculate unused slots to possibly convert to credits
            int unused = maxRequests - currentCount;
            int newCredits = Math.min(maxCredits, window.getCredits() + unused);
            window.setCredits(newCredits);

            return true;
        }

        // If over limit but has credits
        if (window.getCredits() > 0) {
            window.setCredits(window.getCredits() - 1);
            return true;
        }

        // Fully rate limited
        return false;        
    }
    
}
