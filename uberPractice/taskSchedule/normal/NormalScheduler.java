package normal;

import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class NormalScheduler {
    PriorityQueue<Task>queue;
    ExecutorService threads;
    ReentrantLock lock;
    Condition condition;
    NormalScheduler(){
        threads=Executors.newFixedThreadPool(5);
        queue=new PriorityQueue<>();
        lock=new ReentrantLock();
        condition=lock.newCondition();
    }
    void addTask(Task task){
        try{
            lock.lock();
            queue.add(task);
            condition.signalAll();
        }finally{
            lock.unlock();
        }
    }
    void runScheduler(){
        while(!threads.isShutdown()){
            Task task=null;
            try{
                lock.lock();
                if(queue.isEmpty()){
                    condition.await(1000, TimeUnit.MILLISECONDS);
                }
                task=queue.peek();
                if(task==null)continue;
                long timeToSleep=task.scheduleTime-System.currentTimeMillis();
                if(timeToSleep>0){
                    condition.await(timeToSleep, TimeUnit.MILLISECONDS);
                    continue;
                }
                task=queue.poll();                
            }catch(Exception e){}finally{
                lock.unlock();
            }
            threads.submit(task.runnable);
        }
    }
    void stop(){
        threads.shutdownNow();
    }
}
