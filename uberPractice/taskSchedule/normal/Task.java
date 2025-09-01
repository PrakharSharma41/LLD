package normal;

import java.util.Comparator;

public class Task implements Comparable<Task>{
    String id;
    long scheduleTime;
    Runnable runnable;
    long interval; 
    public Task(String id, long scheduleTime,long interval, Runnable runnable) {
        this.id = id;
        this.interval=interval;
        this.scheduleTime = scheduleTime;
        this.runnable = runnable;
    }
    @Override
    public int compareTo(Task o) {
        return Long.compare(this.scheduleTime,o.scheduleTime);
    }
    @Override
    public String toString() {
        return "Task [id=" + id + "]";
    }
    
}