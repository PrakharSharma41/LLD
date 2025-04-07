package producerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource implements Resource{
    private List<Integer>items;
    private int maxItems;
    private ReentrantLock lock;
    private Condition condition;
    public SharedResource(int maxItems) {
        this.maxItems = maxItems;
        items=new LinkedList<>();
        this.lock=new ReentrantLock();
        this.condition=lock.newCondition();
    }
    public void produce(int itemId){
        lock.lock();
        try{
            while(items.size()==maxItems){
                try {
                    System.out.println(Thread.currentThread().getName()+" is waiting to produce");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" produced item "+itemId);
            items.add(itemId);
            condition.signalAll();
        }catch(Exception e){
        }finally{
            lock.unlock();
        }
    }
    public void consume(){
        lock.lock();
        try{
            while(items.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName()+" is waiting to consume");
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName()+" consumed item "+items.removeLast());
            condition.signalAll();   
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }
}
