package com.example.ratelimiter.FixedWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ratelimiter.RateLimiter;

public class CreditsRateLimiterThreadSafeImpl implements RateLimiter{
    Map<String,CreditsFixedWindow>userMap;
    long windowSize;
    int maxRequests;
    int maxCredits;
    public CreditsRateLimiterThreadSafeImpl(long windowSize,int maxRequests,int maxCredits){
        userMap=new ConcurrentHashMap<>();
        this.windowSize=windowSize;
        this.maxRequests=maxRequests;
        this.maxCredits=maxCredits;
    }
    @Override
    public boolean rateLimit(String userId) {
        boolean[] allowed = new boolean[1];

        userMap.compute(userId, (id, window) -> {
            long currentTime = System.currentTimeMillis();
            if (window == null || currentTime - window.getTimeStamp() > windowSize) {
                int leftoverCredits = 0;
                if (window != null) {
                    long timeElapsed = currentTime - window.getTimeStamp();

                    int completeWindowsSkipped = (int)(timeElapsed / windowSize);
                    completeWindowsSkipped = Math.max(0, completeWindowsSkipped);  // avoid negative

                    completeWindowsSkipped = completeWindowsSkipped * maxRequests;
                    int unused = maxRequests - window.getCount();
                    leftoverCredits = Math.min(maxCredits, window.getCredits() + Math.max(0, unused) + completeWindowsSkipped);
                }
                allowed[0] = true;
                return new CreditsFixedWindow(1, leftoverCredits, currentTime);
            }
        
            synchronized (window) {
                if (window.getCount() < maxRequests) {
                    window.setCount(window.getCount() + 1);
                    allowed[0] = true;
                } else if (window.getCredits() > 0) {
                    window.setCredits(window.getCredits() - 1);
                    allowed[0] = true;
                } else {
                    allowed[0] = false;
                }
            }
            return window;
        });
        return allowed[0];
    }   
}
