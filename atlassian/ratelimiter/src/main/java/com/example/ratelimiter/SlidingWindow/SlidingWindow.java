package com.example.ratelimiter.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    private Deque<Long> timestamps;

    public SlidingWindow() {
        this.timestamps = new LinkedList<>();
    }

    public Deque<Long> getTimestamps() {
        return timestamps;
    }

 
}
