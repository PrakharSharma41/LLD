package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import com.example.ratelimiter.RateLimiter;

public class NormalRateLimiterImpl implements RateLimiter{

    HashMap<String,SlidingWindow>userMap;
    long windowSize;
    int maxRequests;

    public NormalRateLimiterImpl(long windowSize,int maxRequests){
        userMap=new HashMap<>();
        this.windowSize=windowSize;
        this.maxRequests=maxRequests;
    }
    @Override
    public boolean rateLimit(String userId) {
        SlidingWindow window=userMap.get(userId);
        long currentTime=System.currentTimeMillis();

        if(window==null){
            window = new SlidingWindow();
            userMap.put(userId, window);            
        }
        Deque<Long> timeStamps = window.getTimestamps();
        while(!timeStamps.isEmpty()&&currentTime-timeStamps.peekFirst()>windowSize){
            timeStamps.pollFirst();
        }
        int currentRequestCount=timeStamps.size();
        if(maxRequests>currentRequestCount){
            timeStamps.add(currentTime);
            return true;
        }
        return false;
    }
    
}
