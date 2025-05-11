package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;


public class SlidingWindowWithCredits extends SlidingWindow{
    int credits;
    private long lastCreditUpdate = 0;
    public SlidingWindowWithCredits() {
        super();
        this.credits = 0;
        this.lastCreditUpdate = System.currentTimeMillis();;
    }
    public long getLastCreditUpdate() {
        return lastCreditUpdate;
    }

    public void setLastCreditUpdate(long lastCreditUpdate) {
        this.lastCreditUpdate = lastCreditUpdate;
    }
    public Deque<Long> getTimestamps() {
        return super.getTimestamps();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;    
    }
}
