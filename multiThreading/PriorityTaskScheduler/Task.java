package PriorityTaskScheduler;

public class Task implements Comparable<Task>{
    private Priority priority;
    private String taskName;
    private long currentTime;
    private TaskStatus taskStatus;
    private Runnable runnable;
    public Task(String taskName,Priority priority, long currentTime, Runnable runnable) {
        this.taskName=taskName;
        this.priority = priority;
        this.currentTime = currentTime;
        this.runnable = runnable;
        this.taskStatus=TaskStatus.CREATED;
    }
    public String getTaskName() {
        return taskName;
    }
    public Priority getPriority() {
        return priority;
    }
    public long getCurrentTime() {
        return currentTime;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public Runnable getRunnable() {
        return runnable;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    @Override
    public int compareTo(Task o) {
        return o.priority.getPriority()-this.priority.getPriority();
    }
}

//  this  o 
//  o this
