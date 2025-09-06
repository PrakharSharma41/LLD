package normal;

import java.util.Comparator;

public class Task implements Comparable<Task>{
    String id;
    long scheduleTime;
    Runnable runnable;
    long interval; 
    TaskType type;
    public Task(String id, long scheduleTime,long interval, Runnable runnable,TaskType type) {
        this.id = id;
        this.interval=interval;
        this.scheduleTime = scheduleTime;
        this.runnable = runnable;
        this.type=type;
    }
    @Override
    public int compareTo(Task o) {
        int cmp= Long.compare(this.scheduleTime,o.scheduleTime);
        if(cmp!=0)return cmp;
        return id.compareTo(o.id);
    }
    @Override
    public String toString() {
        return "Task [id=" + id + "]";
    }
    
}