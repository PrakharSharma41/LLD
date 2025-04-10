import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Scheduler {
    public static void main(String[] args) {
        CustomScheduler customScheduler=new CustomScheduler();
        Runnable task1=customScheduler.getRunnable();
        customScheduler.scheduleJob(task1, 2000);
        customScheduler.start();
        try{
            Thread.sleep(4000);
            customScheduler.closePool();
        }catch(Exception e){

        }
    }
}
