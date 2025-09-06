import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    // Manages state, execution,
    //  and offset — how and when messages are processed
    AtomicInteger offset;
    ISubscriber subscriber;
    Thread thread;

    public TopicSubscriber( ISubscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    @Override
    public String toString() {
        return "TopicSubscriber [subscriber=" + subscriber + "]";
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public synchronized void startConsuming(Topic topic) {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(() -> processMessages(topic));
            thread.start();
        }else{
            wakeUpIfNeeded();
        }
    }

    private void processMessages(Topic topic) {
        while (!Thread.currentThread().isInterrupted()) {
            int curOffset;
            Message message;
            synchronized (this) {
                curOffset = offset.get();
                while (curOffset >= topic.getMessages().size()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }                
            }
            try {
                message = topic.getMessages().get(curOffset);
                subscriber.consume(message);
                offset.compareAndSet(curOffset, curOffset + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public synchronized void stopConsuming() {
        if (thread != null) {
            thread.interrupt();
        }
    }
    public void wakeUpIfNeeded() {
        synchronized (this) {
            notify();
        }
    }
}
