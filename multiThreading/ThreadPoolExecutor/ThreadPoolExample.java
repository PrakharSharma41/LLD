package ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void implement(){
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,4,10,TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(2),new CustomThreadFactory(),new CustomRejectHandler());
        for(int i=0;i<5;i++){ // we want to create 4 tasks
            executor.submit(()->{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("task processed by: "+Thread.currentThread().getName());
            });
        }
        executor.shutdown(); 
    }
}
class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread th=new Thread(r);
        th.setPriority(Thread.NORM_PRIORITY);
        th.setDaemon(false);
        return th;
    }
    
}
class CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected: "+r.toString());
    }
    
}