package repository;

import java.util.HashMap;
import java.util.Map;

import enums.TaskStatus;
import models.Task;
import models.User;
import strategy.TaskFilterStrategy;
import strategy.TaskFilterStrategyImpl;

public class TaskRepository {
    Map<User,Map<Task,Integer>>userToTaskMap;
    Map<String,Task>taskMap;
    TaskFilterStrategy taskFilterStrategy;
    public TaskRepository() {
        userToTaskMap=new HashMap<>();
        taskMap=new HashMap<>();
    }
    public Task createTask(User user,String taskName,Integer date,Integer deadline){
        Task task=new Task(taskName, date,deadline, user, TaskStatus.CREATED);
        if(userToTaskMap.containsKey(user)==false){
            userToTaskMap.put(user,new HashMap<>());
        }
        userToTaskMap.get(user).put(task, null);
        taskMap.put(taskName,task);
        return task;
    }
    public Task modifyTask(String taskName){
        Task task=taskMap.get(taskName);
        if(taskName==null){
            System.out.println("Task with name "+taskName+" does not exist");
            return null;
        }
        task.setTaskName(taskName); 
        return task;    
    }
    public Task completeTask(String taskName){
        Task task=taskMap.get(taskName);
        if(taskName==null){
            System.out.println("Task with name "+taskName+" does not exist");
            return null;
        }
        task.setTaskStatus(TaskStatus.COMPLETED);

        return task;  
    }
    public Integer getTaskDeadline(String taskName){
        return taskMap.get(taskName).getDeadline();
    }
    public boolean deadlineExceddedCheck(Task task,Integer deadline,Integer time){
        boolean expired= deadline<time;
        if(expired){
            task.setTaskStatus(TaskStatus.SPILLED);
            System.out.println("task "+task+" spilled cannot modify");
        }
        return expired;
    }
    public void showAllTaskForUser(User user) {
        Map<Task,Integer>userTasks=userToTaskMap.get(user);
        if(userTasks==null||userTasks.size()==0){
            System.out.println("user "+user+" does not have any task");
            return ;
        }
        for(Task task: userTasks.keySet()){
            if(task.getTaskStatus()!=TaskStatus.REMOVED){
                System.out.println(task);
            }
        }
    }
    public Task removeTask(String taskName){
        Task task=taskMap.get(taskName);
        if(taskName==null){
            System.out.println("Task with name "+taskName+" does not exist");
            return null;
        }
        task.setTaskStatus(TaskStatus.REMOVED);
        return task;
    }
    public void setTaskFilterStrategy(TaskFilterStrategy taskFilterStrategy) {
        this.taskFilterStrategy = taskFilterStrategy;
    }
    public void ListTask(User user,TaskStatus taskStatus){
        taskFilterStrategy.logAllTask(user,taskStatus);
    }
    public void getActivityLog(User user,Integer startTime,Integer endTime){
        taskFilterStrategy.getActivityLog(user,startTime, endTime);
    }
    public Map<Task,Integer> getUserTasks(User user){
        return userToTaskMap.get(user);
    }
}
