package PullBasedQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



 public class InMemoryQueue {
    private final Long retentionPeriod;
    private final ArrayList<Message> queue;
    private final ReentrantReadWriteLock lock;
    private final ScheduledExecutorService cleanupSchedler=Executors.newSingleThreadScheduledExecutor();

    public InMemoryQueue(Long retentionPeriod) {
        this.lock = new ReentrantReadWriteLock();
        this.retentionPeriod = retentionPeriod;
        this.queue = new ArrayList<>();
        cleanupSchedler.scheduleAtFixedRate(this::cleanup, retentionPeriod, 2000, TimeUnit.MILLISECONDS);

    }

    public void publish(Message message) {
        lock.writeLock().lock();
        try {
            queue.add(message);
            System.out.println("Published: " + message);
        } finally {
            lock.writeLock().unlock();
        }
    }
    public void cleanup(){
        lock.writeLock().lock();
        try{
            long nowTime=System.currentTimeMillis();
            System.out.println("running cleanup");
            while(!queue.isEmpty()&&nowTime>queue.get(0).getTimeStamp()+queue.get(0).getTtl()){
                queue.remove(0);
            }    
        }finally{
            lock.writeLock().unlock();
        }
    }
    void shutdown(){
        cleanupSchedler.shutdown();
    }    
    public Message consume(int offset) {
        lock.readLock().lock();
        try {
            if (queue.isEmpty()) {
                return null; // No messages available
            }   
            if(offset>=queue.size()){
                return null;
            }
            Message message = queue.get(offset);
            if(System.currentTimeMillis()>message.getTimeStamp()+message.getTtl()){
                System.out.println(message+" has expired");
                return message;
            }
            System.out.println("Consumed from offset " + offset + ": " + message);
            return message;
        } finally {
            lock.readLock().unlock();
        }
    }
    public int getQueueSize() {
        lock.readLock().lock();
        try {
            return queue.size();
        } finally {
            lock.readLock().unlock();
        }
    }    
}
