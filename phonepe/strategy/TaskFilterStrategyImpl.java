package strategy;

import java.util.Map;

import enums.TaskStatus;
import models.Task;
import models.User;
import repository.TaskRepository;

public class TaskFilterStrategyImpl extends TaskFilterStrategy{
    TaskRepository taskRepository;
    public TaskFilterStrategyImpl(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    @Override
    public void logAllTask(User user,TaskStatus taskStatus) {
        Map<Task,Integer>taskMap=taskRepository.getUserTasks(user);
        for(Task task:taskMap.keySet()){
            if(task.getTaskStatus()==taskStatus){
                System.out.println(task);
            }
        }
    }
    @Override
    public void getActivityLog(User user,Integer startTime, Integer endTime){
        Map<Task,Integer>taskMap=taskRepository.getUserTasks(user);
        for(Task task:taskMap.keySet()){
            if(task.getDate()>=startTime&&task.getDeadline()<=endTime){
                System.out.println(task);
            }
        }
    };    
}
