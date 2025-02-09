import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JobScheduler {
    private final PriorityQueue<ScheduledJob>jobQueue;
    private final ThreadPoolExecutor workerExecutor;
    private final Lock lock;
    private final Condition newTaskAdded;
    public JobScheduler(int workerSize){
        lock=new ReentrantLock();
        newTaskAdded=lock.newCondition();
        this.jobQueue=new PriorityQueue<>(Comparator.comparingLong(ScheduledJob::getScheduledTime));
        this.workerExecutor=(ThreadPoolExecutor)Executors.newFixedThreadPool(workerSize);
    }
    public void schedule(Runnable command,long delay){
        lock.lock();
        try{
            long scheduledTime=System.currentTimeMillis()+delay;
            ScheduledJob job=new ScheduledJob(command, scheduledTime, 0, null, null);
            jobQueue.add(job);
            newTaskAdded.signalAll();
        }finally{
            lock.unlock();
        }
    }
    public void start(){
        long timeToSleep=0;
        while(true){
            if (workerExecutor.isShutdown() && workerExecutor.isTerminated()) {
                System.out.println("Task submission rejected: Executor is not active.");
                break;   
            }
            lock.lock();
            try{
                while(jobQueue.isEmpty()){
                    newTaskAdded.await();
                }
                while (!jobQueue.isEmpty()){
                    timeToSleep = jobQueue.peek().getScheduledTime() - System.currentTimeMillis();
                    if(timeToSleep <= 0){
                        break;
                    }
                    newTaskAdded.await(timeToSleep, TimeUnit.MILLISECONDS);
                }     
                ScheduledJob scheduledJob=jobQueue.poll();  
                System.out.println(scheduledJob);
                long newScheduledTime = 0;
                switch (scheduledJob.getTaskType()) {
                    case 0:
                        workerExecutor.submit(scheduledJob.getRunnable());
                        break;                        
                    case 1:
                        newScheduledTime=System.currentTimeMillis()+scheduledJob.getPeriod();
                        if (!workerExecutor.isShutdown() && !workerExecutor.isTerminated()) {
                            workerExecutor.submit(scheduledJob.getRunnable());
                        } else {
                            System.out.println("Task submission rejected: Executor is not active.");
                        }
                        scheduledJob.setScheduledTime(newScheduledTime);
                        jobQueue.add(scheduledJob);
                        newTaskAdded.signalAll();
                        break;
                    case 2:
                        if (!workerExecutor.isShutdown() && !workerExecutor.isTerminated()) {
                            Future<?> future = workerExecutor.submit(scheduledJob.getRunnable());
                            future.get(); // will wait for the finish of this task
                            newScheduledTime = System.currentTimeMillis()+ scheduledJob.getDelay();
                            scheduledJob.setScheduledTime(newScheduledTime);
                            jobQueue.add(scheduledJob);
                            newTaskAdded.signalAll();       
                        } 
                        else {
                            System.out.println("Task submission rejected: Executor is not active.");
                        }

                        break;                 
                    default:
                        break;
                }         
            }catch (Exception e){
                System.out.println("some thing wrong in start");
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
    public void scheduleAtFixedRate(Runnable command,long initialDelay,long period){
        lock.lock();
        try{
            long scheduledTime=System.currentTimeMillis()+initialDelay;
            ScheduledJob scheduledJob=new ScheduledJob(command, scheduledTime, 1, period, null);
            jobQueue.add(scheduledJob);
            newTaskAdded.signalAll();
        }finally{
            lock.unlock();
        }
    }

    public void scheduleWithFixedDelay(Runnable command,long initialDelay,long delay){
        lock.lock();
        try{
            long scheduledTime=System.currentTimeMillis()+initialDelay;
            ScheduledJob scheduledJob=new ScheduledJob(command, scheduledTime, 2, null, delay);
            jobQueue.add(scheduledJob);
            newTaskAdded.signalAll();
        }finally{
            lock.unlock();
        }
    }
    public void stop(){
        workerExecutor.shutdown();
        try {
            workerExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }
    public static void main(String[] args) {
        JobScheduler schedulerService = new JobScheduler(10);
        schedulerService.schedule(getRunnableTask("Task1"), 1000);
        schedulerService.scheduleAtFixedRate(getRunnableTask("Task2"),1, 1000);
        schedulerService.scheduleWithFixedDelay(getRunnableTask("Task3"),1,2000);
        schedulerService.scheduleAtFixedRate(getRunnableTask("Task4"),1, 2000);
        Thread schedulerThread = new Thread(schedulerService::start);
        schedulerThread.start();
        try{
            Thread.sleep(10000); // Run for 10 seconds before stopping
            schedulerService.stop();
            // schedulerThread.join(); // Wait for graceful shutdown            
        }catch(Exception e){System.out.println(e);}
    }
    private static Runnable getRunnableTask(String s) {
        return () -> {
            System.out.println(s +" started at " + System.currentTimeMillis() / 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s +" ended at " + System.currentTimeMillis() / 1000);
        };
    }
}
class ScheduledJob{
    private Runnable runnable;
    private Long scheduledTime;
    private int TaskType;
    private Long delay;
    private Long period;
    public ScheduledJob(Runnable runnable, Long scheduledTime, int taskType, Long period, Long delay) {
        this.runnable = runnable;
        this.scheduledTime = scheduledTime;
        TaskType = taskType;
        this.delay = delay;
        this.period = period;
    }
    public Runnable getRunnable() {
        return runnable;
    }
    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
    public Long getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(Long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    public int getTaskType() {
        return TaskType;
    }
    public void setTaskType(int taskType) {
        TaskType = taskType;
    }
    public Long getDelay() {
        return delay;
    }
    public void setDelay(Long delay) {
        this.delay = delay;
    }
    @Override
    public String toString() {
        return "ScheduledJob [scheduledTime=" + scheduledTime + ", TaskType=" + TaskType + ", delay=" + delay
                + ", period=" + period + "]";
    }
    public Long getPeriod() {
        return period;
    }
    public void setPeriod(Long period) {
        this.period = period;
    }
    
}