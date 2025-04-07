package PriorityTaskScheduler;

public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);
    int priority;
    Priority(int p){
        this.priority=p;
    }
    public int getPriority(){
        return priority;
    }
}
