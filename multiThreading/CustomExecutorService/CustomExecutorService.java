package CustomExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomExecutorService {
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();    
    private volatile boolean isRunning = true;
    private final List<Thread> workers = new ArrayList<>();

    CustomExecutorService(){
        for(int i=0;i<1;i++){
            Thread worker=new Thread(()->{
                while (isRunning || !taskQueue.isEmpty()) {
                    try {
                        Runnable task = taskQueue.take(); // blocks until task is available
                        task.run();
                    } catch (InterruptedException e) {
                        if (!isRunning) break;
                    }
                }
            });
            workers.add(worker);
            worker.start();
        }
    }
    CustomExecutorService(int x){
        for(int i=0;i<1;i++){
            Thread worker=new Thread(()->{
                while(isRunning || !taskQueue.isEmpty()){
                    Runnable task = null;
                    synchronized(taskQueue){
                        while (taskQueue.isEmpty()) {
                            try {
                                taskQueue.wait();
                                if (!isRunning) return;
                            } catch (InterruptedException e) {
                                return; // thread was interrupted to shutdown
                            }
                        }
                        task = taskQueue.remove();
                        task.run();
                    }
                }
            });
            worker.start();
            workers.add(worker);
        }
    }
    public void submitTask(Runnable task){
        if(isRunning){
            taskQueue.offer(task);
        }else{
            System.out.println("executor is not running");
        }
    }
    public void shutdown() {
        isRunning = false;
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
}
