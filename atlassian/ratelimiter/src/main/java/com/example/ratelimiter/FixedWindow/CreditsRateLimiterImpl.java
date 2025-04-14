package com.example.ratelimiter.FixedWindow;

import java.util.HashMap;

import com.example.ratelimiter.RateLimiter;

public class CreditsRateLimiterImpl implements RateLimiter{
    HashMap<String,CreditsFixedWindow>userMap;
    long windowSize;
    int maxRequests;
    int maxCredits;
    public CreditsRateLimiterImpl(long windowSize,int maxRequests,int maxCredits){
        userMap=new HashMap<>();
        this.windowSize=windowSize;
        this.maxRequests=maxRequests;
        this.maxCredits=maxCredits;
    }
    @Override
    public boolean rateLimit(String userId) {
        CreditsFixedWindow window=userMap.get(userId);
        long currentTime=System.currentTimeMillis();
        if(window==null||currentTime-window.getTimeStamp()>windowSize){
            int leftoverCredits = 0;            
            if (window != null) {
                int completeWindowsSkipped = (int)(currentTime-(windowSize+window.getTimeStamp()))/(int)windowSize;
                completeWindowsSkipped =completeWindowsSkipped*maxRequests;
    
                int unused = maxRequests - window.getCount();
                leftoverCredits = Math.min(maxCredits, window.getCredits() + Math.max(0, unused)+completeWindowsSkipped);
            }
            window = new CreditsFixedWindow(1, leftoverCredits, currentTime);
            userMap.put(userId, window);
            return true;            
        }
        
        if(window.getCount()<maxRequests){
            window.setCount(window.getCount()+1);
            return true;
        }
        if(window.getCredits()>0){
            window.setCredits(window.getCredits()-1);
            return true;
        }
        return false;
    }
    
}
