package DelayedTask;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class DelayedQueue {
    private PriorityQueue<DelayedTask> queue=new PriorityQueue<>();
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(true);
    public void putTask(DelayedTask task){
        synchronized(queue){
            queue.add(task);
            queue.notify();
        }
    }
    public DelayedQueue(){
        worker=new Thread(this::start);
        worker.start();
    }
    public void start(){
        while(running.get()){
            // System.out.println("jhabskjd");
            try{
                DelayedTask taskToRun = null;
                long timeToWait = 0;                
                synchronized(queue){
                    if (queue.isEmpty()) {
                        queue.wait();
                    }    
                    DelayedTask nextTask = queue.peek();
                    long now = System.currentTimeMillis();

                    if (now >= nextTask.getExecutionTime()) {
                        taskToRun = queue.poll();
                    } else {
                        timeToWait = nextTask.getExecutionTime() - now;
                        queue.wait(timeToWait); // Wait until the task is ready
                    }
                }  
                if (taskToRun != null) {
                    try {
                        taskToRun.getTask().run();
                    } catch (Exception e) {
                        System.err.println("Task execution failed: " + e.getMessage());
                    }
                }                  
            }catch(Exception e){

            }
        }
    }
    public void shutdown() {
        running.set(false);
        worker.interrupt();
    }    
}
