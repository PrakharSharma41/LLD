package com.example.ratelimiter.FixedWindow;

public class CreditsFixedWindow extends FixedWindow{
    private int credits;
    public CreditsFixedWindow(int count,int credits, long timeStamp) {
        super(count, timeStamp);
        this.credits=credits;
    }
    public int getCount() {
        return super.getCount();
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public long getTimeStamp() {
        return super.getTimeStamp();
    }
    public void setCount(int count) {
        super.setCount(count);
    }
    public void setTimeStamp(long timeStamp) {
        super.setTimeStamp(timeStamp);
    }
        
}
