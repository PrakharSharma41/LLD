package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;


public class SlidingWindowWithCredits extends SlidingWindow{
    int credits;
    public SlidingWindowWithCredits() {
        super();
        this.credits = 0;
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
