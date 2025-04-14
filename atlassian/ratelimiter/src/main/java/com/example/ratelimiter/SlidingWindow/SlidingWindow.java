package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    private Deque<Long> timestamps;
    private int credits;

    public SlidingWindow() {
        this.timestamps = new LinkedList<>();
        this.credits = 0;
    }

    public Deque<Long> getTimestamps() {
        return timestamps;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }    
}
