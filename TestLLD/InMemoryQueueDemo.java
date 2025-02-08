package TestLLD;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

class Message{
    String text;
    long timestamp;
    long ttl;
    public Message(String text, long ttl) {
        this.text = text;
        this.timestamp = System.currentTimeMillis();
        this.ttl = ttl;
    }
    public String getText() {
        return text;
    }
    public long getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "Message [text=" + text + ", timestamp=" + timestamp + ", ttl=" + ttl + "]";
    }
    public long getTtl() {
        return ttl;
    }
    public boolean isExpired() {
        return ttl > 0 && System.currentTimeMillis() - timestamp > ttl;
    }
}
class InMemoryQueue{
    Queue<Message>queue=new LinkedList<>();
    ReentrantLock lock=new ReentrantLock();
    public void publish(String text,long ttl){
        Message message=new Message(text, ttl);
        lock.lock();
        try{
            queue.add(message);
            System.out.println("message published is "+message);
        }finally{
            lock.unlock();
        }
    }
    public  String consume(){
        lock.lock();
        try{
            while(!queue.isEmpty()){
                Message message=queue.peek();
                queue.poll();
                if(message.isExpired()){
                    System.out.println("message expired");
                }else{
                    System.out.println("message consumed is "+message);
                    return message.toString();
                }
            }
        }finally{
            lock.unlock();

        }        
        return null;
    }
}
class Publisher implements Runnable{
    InMemoryQueue queue;
    private final String[] messages;
    private final long ttl;
    public Publisher(InMemoryQueue queue, String[] messages, long ttl) {
        this.queue = queue;
        this.messages = messages;
        this.ttl = ttl;
    }

    @Override
    public void run() {
        for(String msg:messages){
            queue.publish(msg, ttl);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
class Consumer implements Runnable{
    private final InMemoryQueue queue;

    public Consumer(InMemoryQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            String message=queue.consume();
            if(message!=null){
                try{
                    System.out.println("sleeping");
                    Thread.sleep(500);
                }catch(Exception e){
                    Thread.currentThread().interrupt();
                }                
            }else{
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
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
