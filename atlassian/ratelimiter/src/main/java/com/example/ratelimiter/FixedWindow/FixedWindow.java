package com.example.ratelimiter.FixedWindow;

public class FixedWindow {
    private int count;
    private long timeStamp;
    public FixedWindow(int count, long timeStamp) {
        this.count = count;
        this.timeStamp = timeStamp;
    }
    public int getCount() {
        return count;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
