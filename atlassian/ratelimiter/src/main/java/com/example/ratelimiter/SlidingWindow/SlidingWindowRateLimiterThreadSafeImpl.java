package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ratelimiter.RateLimiter;

public class SlidingWindowRateLimiterThreadSafeImpl implements RateLimiter{
    private final Map<String, SlidingWindowWithCredits> userMap = new ConcurrentHashMap<>();
    private final long windowSize;
    private final int maxRequests;
    private final int maxCredits;
    public SlidingWindowRateLimiterThreadSafeImpl(long windowSize, int maxRequests, int maxCredits) {
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
    }
    @Override
    public boolean rateLimit(String userId) {
        boolean[] allowed = new boolean[1];
        
        userMap.compute(userId, (id,window)->{
            long currentTime = System.currentTimeMillis();
            if(window==null){
                window=new SlidingWindowWithCredits();
                return window;
            }
            synchronized(window){

                Deque<Long> timestamps = window.getTimestamps();

                // Remove expired timestamps
                while (!timestamps.isEmpty() && currentTime - timestamps.peekFirst() > windowSize) {
                    timestamps.pollFirst();
                }

                if (window.getLastCreditUpdate() + windowSize <= currentTime) {
                    // Calculate unused slots to possibly convert to credits
                    int unusedSlots = maxRequests - timestamps.size();
                    int skippedWindowCredits=(int)((currentTime-window.getLastCreditUpdate())/windowSize*maxRequests);
                    int newCredits = Math.min(maxCredits, window.getCredits() + unusedSlots+skippedWindowCredits);
                    window.setCredits(newCredits);
                    window.setLastCreditUpdate(currentTime); // Set last credit update time
                }

                int currentCount = timestamps.size();
                if (currentCount < maxRequests) {
                    timestamps.add(currentTime);
                    allowed[0]=true;
                }else if (window.getCredits() > 0) {
                    window.setCredits(window.getCredits() - 1);
                    allowed[0]=true;
                }else{
                    allowed[0]=false;
                }
            }
            return window;
        });
        return allowed[0];
     
    }    
}
