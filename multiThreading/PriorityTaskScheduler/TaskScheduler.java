package PriorityTaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {
    private PriorityBlockingQueue<Task> taskQueue;
    private ExecutorService workerExecutor ;
    private final ExecutorService schedulerThread;
    public TaskScheduler(int workerThreadSize) {
        taskQueue=new PriorityBlockingQueue<>();
        workerExecutor =  Executors.newFixedThreadPool(workerThreadSize);
        this.schedulerThread = Executors.newSingleThreadExecutor();
    }
    public void scheduleTask(Task task){
        taskQueue.add(task);        
    }
    public void shutdown() {
        schedulerThread.shutdownNow();
        workerExecutor.shutdown();
    }    
    public void start(){
        schedulerThread.submit(() -> {
            while (true) {
                try {
                    Task task = taskQueue.take(); // blocks until a task is available
                    task.setTaskStatus(TaskStatus.INPROGRESS);
                    workerExecutor.submit(() -> {
                        try {
                            task.getRunnable().run();
                            task.setTaskStatus(TaskStatus.COMPLETED);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });        
    }
    public Runnable getRunnableTask(String s) {
        return () -> {
            System.out.println(s +" started at " + System.currentTimeMillis() / 1000);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s +" ended at " + System.currentTimeMillis() / 1000);
        };
    }
}
