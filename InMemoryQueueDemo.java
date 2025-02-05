import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Message {
    String content;
    long timestamp;
    long ttl;  // Time-to-live in milliseconds. 0 means no TTL.

    public Message(String content, long ttl) {
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.ttl = ttl;
    }

    public boolean isExpired() {
        return ttl > 0 && System.currentTimeMillis() - timestamp > ttl;
    }
}

class InMemoryQueue {
    private final Queue<Message> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    // Publisher thread that adds messages to the queue
    public void publish(String content, long ttl) {
        Message message = new Message(content, ttl);
        lock.lock();  // Acquire the lock
        try {
            queue.offer(message);
            System.out.println("Published: " + content);
        } finally {
            lock.unlock();  // Always release the lock
        }
    }

    // Consumer thread that reads messages from the queue
    public String consume() {
        lock.lock();  // Acquire the lock
        try {
            while (!queue.isEmpty()) {
                Message message = queue.peek();
                // Check if the message is expired
                if (message.isExpired()) {
                    queue.poll();  // Discard expired message
                    System.out.println("Discarded expired message.");
                } else {
                    queue.poll();  // Consume the message
                    System.out.println("Consumed: " + message.content);
                    return message.content;
                }
            }
        } finally {
            lock.unlock();  // Always release the lock
        }
        return null;  // Return null if the queue is empty
    }

    // Return the size of the queue
    public int size() {
        lock.lock();  // Acquire the lock
        try {
            return queue.size();
        } finally {
            lock.unlock();  // Always release the lock
        }
    }
}

class Publisher implements Runnable {
    private final InMemoryQueue queue;
    private final String[] messages;
    private final long ttl;

    public Publisher(InMemoryQueue queue, String[] messages, long ttl) {
        this.queue = queue;
        this.messages = messages;
        this.ttl = ttl;
    }

    @Override
    public void run() {
        for (String message : messages) {
            queue.publish(message, ttl);
            try {
                Thread.sleep(1000);  // Simulate time delay between publishing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final InMemoryQueue queue;

    public Consumer(InMemoryQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            String message = queue.consume();
            if (message != null) {
                try {
                    Thread.sleep(500);  // Simulate message processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                try {
                    Thread.sleep(1000);  // Wait for new messages
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

public class InMemoryQueueDemo {
    public static void main(String[] args) {
        InMemoryQueue queue = new InMemoryQueue();

        // Create Publishers
        Publisher publisher1 = new Publisher(queue, new String[]{"Message 1", "Message 2", "Message 3"}, 3000); // TTL of 3 seconds
        Publisher publisher2 = new Publisher(queue, new String[]{"Message 4", "Message 5", "Message 6"}, 3000); // TTL of 3 seconds

        // Create Consumers
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        // Start threads
        Thread publisherThread1 = new Thread(publisher1);
        Thread publisherThread2 = new Thread(publisher2);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);

        publisherThread1.start();
        publisherThread2.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}
