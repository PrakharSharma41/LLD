package ReentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    ReentrantLock lock=new ReentrantLock();
    List<Integer>items=new ArrayList<>();
    Condition condition = lock.newCondition();
    public void producer(){
        try{
            lock.lock();
            if(items.size()==5){
                System.out.println("need to wait for items list to become empty "+Thread.currentThread().getName());
                condition.await();
            }          
            int x=(int)(Math.random()*400);
            System.out.println(Thread.currentThread().getName()+" adding "+x);  
            items.add(x);
            condition.signalAll();
            Thread.sleep(2000);
        }catch(Exception e){
        }finally{
            lock.unlock();
        }
    }
    public void consume(){
        try{
            lock.lock();
            if(items.size()==0){
                System.out.println("need to wait to add items to the list "+Thread.currentThread().getName());
                condition.await();
            }            
            System.out.println("item consumed by "+items.removeLast()+" "+Thread.currentThread().getName());
            condition.signalAll();
            Thread.sleep(2000);
        }catch(Exception e){}
        finally{
            lock.unlock();
        }
    }
}
