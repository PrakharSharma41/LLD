package uber;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.*;

// Main class should be named 'Solution' and should not be public.


/*
Implement an InMemory Task scheduler Library that supports these functionalities:
Submit a task and a time at which the task should be executed. --> schedule(task, time)

time => epoch seconds / millis


t = 0

task -> print "hello" at t = 5

thread

t = 5 => hello
0 
1
2
3
4
5
task(5)
task(6)
task(7)
*/
class TaskScheduler{
    private PriorityQueue<ScheduledTask>taskQueue;
    private ThreadPoolExecutor workerExecutor;
    private Lock lock;
    
    public TaskScheduler(int poolSize){
        lock=new ReentrantLock();
        workerExecutor=(ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);
        taskQueue=new PriorityQueue<>(Comparator.comparingLong(ScheduledTask::getScheduledTime));   
    }
    public void schedule(Runnable command,Long delay){
        lock.lock();
        try{
            Long scheduledTime=System.currentTimeMillis()+delay;
            ScheduledTask scheduledTask=new ScheduledTask(command,scheduledTime);
            taskQueue.add(scheduledTask);
        }finally{
            lock.unlock();
        }
    }
    public void startExecutor(){
        // System.out.println("execution start "+workerExecutor.isShutdown());
        while(!workerExecutor.isShutdown()||!workerExecutor.isTerminated()){
            while(taskQueue.isEmpty()){
                try{
                    Thread.sleep(1000);                    
                }
                catch(Exception e){
                    System.out.println("exception occured "+e);
                }
                continue;
            }
            while(!taskQueue.isEmpty()){
                Long timeToSleep=taskQueue.peek().getScheduledTime()-System.currentTimeMillis();
                if(timeToSleep<=0){
                    break;
                }        
                try{
                    Thread.sleep(timeToSleep);                    
                }
                catch(Exception e){
                    System.out.println("exception occured "+e);
                }                
            }
            ScheduledTask scheduledTask=taskQueue.poll();
            workerExecutor.submit(scheduledTask.getRunnable());
        }
    }
    public void stopExecutor(){
        workerExecutor.shutdown();
        // workerEecutor.awaitTermination(Long.MAX_VALUE,Time);
    }
}
class ScheduledTask{
    private Runnable runnable;
    private Long scheduledTime; // in seconds
    
    public ScheduledTask(Runnable runnable,Long scheduledTime ){
        this.runnable=runnable;
        this.scheduledTime=scheduledTime;
    }
    public Runnable getRunnable(){
        return runnable;
    }
    public Long getScheduledTime(){
        return scheduledTime;
    }
    
}
class Solution {
    public static Runnable getRunnableTask(String s){
        return ()->{
            System.out.println("Task started at "+System.currentTimeMillis()+" printing "+s);  
        };
    }
    public static void main(String[] args) {
        TaskScheduler taskScheduler=new TaskScheduler(10);
        taskScheduler.schedule(getRunnableTask("Task1"),5000L);
        taskScheduler.schedule(getRunnableTask("Task2"),7000L);
        taskScheduler.schedule(getRunnableTask("Task3"),3000L);
        Thread mainThread=new Thread(taskScheduler::startExecutor);
        mainThread.start();
        
    }
}
