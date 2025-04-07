package OptimisticLockingExample;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Test {
    public static void main(String[] args) {
        Thread[] thread=new Thread[10000];
        AtomicInteger count=new AtomicInteger(0);
        for(int i=0;i<10000;i++){
            thread[i]=new Thread(()->{
                int localCounter=count.get();
                do {
                    localCounter = count.get(); // Get the current counter value

                // Compare-and-swap operation
                } while (!count.compareAndSet(localCounter, localCounter + 1));
            });
            thread[i].start();
        }
        System.out.println("count is "+count);
    }
}
