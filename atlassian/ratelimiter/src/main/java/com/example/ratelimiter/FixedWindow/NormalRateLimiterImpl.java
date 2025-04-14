package com.example.ratelimiter.FixedWindow;

import java.util.HashMap;

import com.example.ratelimiter.RateLimiter;

public class NormalRateLimiterImpl implements RateLimiter{
    HashMap<String,FixedWindow>userMap;
    long windowSize;
    int maxRequests;
    public NormalRateLimiterImpl(long windowSize,int maxRequests){
        userMap=new HashMap<>();
        this.windowSize=windowSize;
        this.maxRequests=maxRequests;
    }
    @Override
    public boolean rateLimit(String userId) {
        FixedWindow window=userMap.get(userId);
        long currentTime=System.currentTimeMillis();
        if(window==null||window.getTimeStamp()-currentTime>windowSize){
            window=new FixedWindow(1, currentTime);
            userMap.put(userId, window);
            return true;
        }else{
            if(window.getCount()<maxRequests){
                window.setCount(window.getCount()+1);
                userMap.put(userId, window);
                return true;
            }else{
                return false;
            }
        }
    }    
}
