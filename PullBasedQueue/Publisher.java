package PullBasedQueue;

public class Publisher implements Runnable {
    private final InMemoryQueue inMemoryQueue;

    public Publisher(InMemoryQueue inMemoryQueue) {
        this.inMemoryQueue = inMemoryQueue;
    }

    public void publish(Message message) {
        inMemoryQueue.publish(message);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Message message = new Message("Message " + i, System.currentTimeMillis(), 4000L);
            publish(message);
            try {
                Thread.sleep(2000);  // Simulate delay in publishing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
