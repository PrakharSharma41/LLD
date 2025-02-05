package models;

import enums.TaskStatus;

public class Task{
    String taskName;
    Integer date;
    Integer deadline;
    public Integer getDeadline() {
        return deadline;
    }
    User user;
    TaskStatus taskStatus;
    public Task(String taskName, Integer date,Integer deadline, User user, TaskStatus taskStatus) {
        this.taskName = taskName;
        this.date = date;
        this.user = user;
        this.taskStatus = taskStatus;
        this.deadline=deadline;
    }
    @Override
    public String toString() {
        return "Task [taskName=" + taskName + ", date=" + date + ", deadline=" + deadline + ", taskStatus=" + taskStatus
                + "]";
    }
    public String getTaskName() {
        return taskName;
    }
    public Integer getDate() {
        return date;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public User getUser() {
        return user;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

}