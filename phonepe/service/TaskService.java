package service;

import enums.TaskStatus;
import models.Task;
import models.User;
import repository.TaskRepository;
import strategy.TaskFilterStrategyImpl;

public class TaskService {
    TaskRepository taskRepository;
    UserService userService;
    public TaskService(UserService userService) {
        taskRepository=new TaskRepository();
        this.userService=userService;
    }
    public Task createTask(String  userName,String taskName,Integer date,Integer deadline){
        User user=userService.getUser(userName);
        //check
        Task task=taskRepository.createTask(user, taskName, date, deadline);
        return task;
    }
    public Task modifyTask(String taskName){
        Task task=taskRepository.modifyTask(taskName);
        return task;
    }
    public Task completeTask(String taskName){
        Task task=taskRepository.completeTask(taskName);
        //remove task
        return task;
    }
    public Task removeTask(String taskName){
        Task task=taskRepository.removeTask(taskName);
        return task;
    }

    public void showAllTaskForUser(String userName){
        User user=userService.getUser(userName);
        taskRepository.showAllTaskForUser(user);
    }
    public void listTask(String userName,TaskStatus taskStatus){
        taskRepository.setTaskFilterStrategy(new TaskFilterStrategyImpl(taskRepository));
        User user=userService.getUser(userName);
        taskRepository.ListTask(user,taskStatus);
    }
    public void getActivityLog(String userName,Integer startTime,Integer endTime){
        taskRepository.setTaskFilterStrategy(new TaskFilterStrategyImpl(taskRepository));
        User user=userService.getUser(userName);
        taskRepository.getActivityLog(user, startTime, endTime);
    }
}
