package PullBasedQueue;

import java.util.concurrent.atomic.AtomicInteger;


public class Consumer implements Runnable {
    private final InMemoryQueue inMemoryQueue;
    private int offset; // Track the offset
    private int lastQueueSize; // Store last known queue size

    public Consumer(InMemoryQueue inMemoryQueue) {
        this.inMemoryQueue = inMemoryQueue;
        this.offset = 0;
        this.lastQueueSize = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000); // Simulate periodic pulling
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Adjust offset if cleanup removed messages
            int currentSize = inMemoryQueue.getQueueSize();
            if (offset > 0 && currentSize < lastQueueSize) {
                int removedCount = lastQueueSize - currentSize;
                offset = Math.max(0, offset - removedCount); // Adjust offset to avoid out-of-bounds issues
                System.out.println("Offset adjusted to " + offset + " after cleanup removed " + removedCount + " messages.");
            }

            // Try consuming the message
            Message message = inMemoryQueue.consume(offset);
            if (message != null) {
                offset++; // Move to next message
            }

            // Update last known queue size
            lastQueueSize = currentSize;
        }
    }
}
