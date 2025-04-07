package producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SharedResource1 implements Resource{
    private final BlockingQueue<Integer> queue;

    public SharedResource1(int maxItems) {
        this.queue = new ArrayBlockingQueue<>(maxItems);
    }

    public void produce(int item) {
        try {
            queue.put(item);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // blocks if full
        System.out.println("Produced: " + item);
    }

    public void consume() {
        int item=0;
        try {
            item = queue.take();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // blocks if empty
        System.out.println(Thread.currentThread().getName() + " consumed: " + item);
    }
}
