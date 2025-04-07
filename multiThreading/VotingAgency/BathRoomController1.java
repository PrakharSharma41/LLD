package VotingAgency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BathRoomController1 {
    private BathRoom bathRoom;
    private ExecutorService workers;
    private ExecutorService scheduler;
    private LinkedBlockingDeque<User>democrats,republican;
    private ReentrantLock lock;
    private Condition condition;
    public BathRoomController1(BathRoom bathRoom) {
        this.bathRoom = bathRoom;
        workers=Executors.newFixedThreadPool(5);
        democrats=new LinkedBlockingDeque<User>();
        republican=new LinkedBlockingDeque<User>();
        scheduler=Executors.newFixedThreadPool(3);
        lock=new ReentrantLock();
        condition=lock.newCondition();
    }
    public void addUser(User user){
        lock.lock();
        try {
            if (user.getUserType() == UserType.DEMOCRAT) {
                democrats.add(user);
            } else {
                republican.add(user);
            }
            condition.signal();  // Wake up the scheduler
        } finally {
            lock.unlock();
        }
    }
    public void shutdown(){
        scheduler.shutdown();
        workers.shutdown();
        try{
            scheduler.awaitTermination(10, TimeUnit.SECONDS);
            workers.awaitTermination(10, TimeUnit.SECONDS);    
        }catch(Exception e){}
    }
    public void start(){
        scheduler.submit(()->{
            while(true){
                lock.lock();
                try {
                    while (democrats.isEmpty() && republican.isEmpty()) {
                        condition.await(4000,TimeUnit.MILLISECONDS);  // Wait until users are available
                    }
                } catch (InterruptedException e) {} finally {
                    lock.unlock();
                }           
                User user = null;
                UserType currentType = bathRoom.getBathroomStatus();
                if (currentType == null) {
                    user = !democrats.isEmpty() ? democrats.pollFirst() : republican.pollFirst();
                }else if(currentType==UserType.DEMOCRAT&&!democrats.isEmpty()){
                    user=democrats.pollFirst();
                }else if(currentType==UserType.REPUBLICAN&&!republican.isEmpty()){
                    user=republican.pollFirst();
                }
                if(user==null)continue;
                if(bathRoom.tryEnter(user)){
                    final User userIn = user;
                    workers.submit(()->{
                        try{
                            System.out.println("user "+userIn.getId()+" has occupied with usertype as "+userIn.getUserType());
                            Thread.sleep(userIn.getBathRoomTimeInMillis());
                            System.out.println("user "+userIn.getId()+" is now exiting ");
                            bathRoom.exit();
                        }catch(Exception e){}
                    });
                }else{
                    // System.out.println("user "+user.getId()+" failed to occupy with usertype as "+user.getUserType());
                    addUser(user);
                }
                // try{
                //     Thread.sleep(2000);//sleep to prevent some cycle
                // }catch(Exception e){

                // }
            }
        });
    }
}
