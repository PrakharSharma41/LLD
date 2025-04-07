package DelayedTask;

public class DelayedTask implements Comparable<DelayedTask>{

    private Runnable task;
    private long executionTime;
    public DelayedTask(Runnable task, long delayInMillis) {
        this.task = task;
        this.executionTime = System.currentTimeMillis() + delayInMillis;
    }
    @Override
    public int compareTo(DelayedTask other) {
        return Long.compare(this.executionTime, other.executionTime);
    }
    @Override
    public String toString() {
        return "DelayedTask [executionTime=" + executionTime + "]";
    }
    public Runnable getTask() {
        return task;
    }
    public long getExecutionTime() {
        return executionTime;
    }
    
}
