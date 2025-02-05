package strategy;

import java.util.List;

import enums.TaskStatus;
import models.Task;
import models.User;

public abstract class TaskFilterStrategy {
    public abstract void logAllTask(User user,TaskStatus taskStatus);
    public abstract void getActivityLog(User user,Integer startTime,Integer endTime);
}
