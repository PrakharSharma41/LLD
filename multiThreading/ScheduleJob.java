public class ScheduleJob {
    private Runnable runnable;
    private long scheduledTime;
    ScheduleJob(Runnable runnable,long scheduledTime){
        this.runnable=runnable;
        this.scheduledTime=scheduledTime;
    }
    public Runnable getRunnable() {
        return runnable;
    }
    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
    public long getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    
}
