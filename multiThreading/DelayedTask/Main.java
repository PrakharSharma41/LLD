package DelayedTask;

import java.util.concurrent.DelayQueue;

public class Main {
    public static void main(String[] args) {
        DelayedQueue delayQueue = new DelayedQueue();

        delayQueue.putTask(new DelayedTask(() -> System.out.println("Hello after 3s"),3000));
        delayQueue.putTask(new DelayedTask(() -> System.out.println("Hello after 1s"), 1000));
        delayQueue.putTask(new DelayedTask(() -> System.out.println("Hello after 2s"), 2000));

        // Give it time to run
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
        delayQueue.shutdown();        
    }
}
