import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomScheduler {
    private ExecutorService executors;
    private ReentrantLock lock;
    private Condition condition;
    private PriorityQueue<ScheduleJob>taskQueue;
    public CustomScheduler() {
        executors=Executors.newFixedThreadPool(10);
        lock=new ReentrantLock();
        condition=lock.newCondition();
        taskQueue=new PriorityQueue<>(Comparator.comparingLong(ScheduleJob::getScheduledTime));
    }
    public void start(){
        new Thread(()->{
            try{
                lock.lock();
                while(taskQueue.isEmpty()){
                    condition.await();
                }    
                long timeToSleep=0;
                while(!taskQueue.isEmpty()){
                    timeToSleep=taskQueue.peek().getScheduledTime()-System.currentTimeMillis();
                    if(timeToSleep<0)break;
                    condition.await(timeToSleep, TimeUnit.MILLISECONDS);
                }
                ScheduleJob job=taskQueue.poll();
                executors.submit(job.getRunnable());
            }catch(Exception e){
            }finally{
                lock.unlock();
            }
        }).start();
    }
    public void scheduleJob(Runnable runnable, long delay){
        try{
            lock.lock();
            long scheduledTime = System.currentTimeMillis() + delay;
            taskQueue.add(new ScheduleJob(runnable, scheduledTime));
            System.out.println("task scheduled for "+scheduledTime);
            condition.signalAll();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }
    public Runnable getRunnable(){
        Runnable task=()->{
            try{
                System.out.println(Thread.currentThread().getName()+" has started running at "+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+" has completed running");
            }catch(Exception e){
            }    
        };
        return task;
    }
    public void closePool(){
        executors.shutdown();
    }
}
