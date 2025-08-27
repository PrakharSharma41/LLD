package normal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    PriorityBlockingQueue<Task>taskQueue;
    ExecutorService threads;
    TaskScheduler(){
        threads=Executors.newFixedThreadPool(5);
        taskQueue=new PriorityBlockingQueue<>();
    }
    void addTask(Task task){
        taskQueue.add(task);
    }
    void runScheduler(){
        while(!threads.isShutdown()){
            try {
                Task task=taskQueue.poll(100,TimeUnit.MILLISECONDS);
                if(task!=null){
                    long delay = task.scheduleTime - System.currentTimeMillis();
                    if (delay > 0) {
                        taskQueue.offer(task);
                        Thread.sleep(Math.min(delay, 100)); 
                    } else {
                        threads.submit(task.runnable);
                        task.scheduleTime=System.currentTimeMillis()+task.interval;
                        taskQueue.offer(task);
                    }  
                }              
            } catch (InterruptedException e) {
            }
        }
    }
    void stop(){
        threads.shutdownNow();
    }
}
