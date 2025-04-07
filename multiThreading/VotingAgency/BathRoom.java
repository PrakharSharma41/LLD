package VotingAgency;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BathRoom {
    private int MAX_OCCUPANCY;
    UserType currentUserType;
    private ReentrantLock lock;
    private int currentOccupancy=0;
    public BathRoom(int mAX_OCCUPANCY) {
        MAX_OCCUPANCY = mAX_OCCUPANCY;
        currentUserType=null;
        lock=new ReentrantLock();
    }
    public UserType getBathroomStatus(){
        return currentUserType;
    }
    public boolean tryEnter(User user ){
        UserType userType=user.getUserType();
        lock.lock();
        // System.out.println("user with id "+user.getId()+" is trying to enter");
        try{
            if((currentUserType==null|| userType==currentUserType)&&(currentOccupancy<MAX_OCCUPANCY)){
                currentOccupancy++;currentUserType=userType;
                return true;
            }
        }catch(Exception e){}
        finally{
            lock.unlock();
        }
        return false;
    }
    public void exit(){
        lock.lock();
        try{
            currentOccupancy--;
            if(currentOccupancy==-1)currentOccupancy=0;
            if(currentOccupancy==0){
                currentUserType=null;
                System.out.println("bathroom is now empty");
            }
        }catch(Exception e){}
        finally{
            lock.unlock();
        }
    }
    public UserType getCurrentUserType() {
        lock.lock();
        try {
            return currentUserType;
        } finally {
            lock.unlock();
        }
    }    
    
}
