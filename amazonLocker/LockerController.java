package amazonLocker;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LockerController {
    private final Map<Integer, Locker> freeLocker;
    private PriorityQueue<ExpirationEntry>expiryQueue;
    private ScheduledExecutorService cleanupScheduler;
    private LockerStrategy lockerStrategy;
    OtpHandler otpHandler;
    private final Map<Integer, String> otpMap = new ConcurrentHashMap<>();
    LockerController(){
        freeLocker= new ConcurrentHashMap<>();
        expiryQueue=new PriorityQueue<>();
        cleanupScheduler=Executors.newSingleThreadScheduledExecutor();
        // cleanupScheduler.scheduleAtFixedRate(this::cleanup, 1000, 10000, TimeUnit.MILLISECONDS);        
        lockerStrategy=new RandomLockerImpl(freeLocker);
        otpHandler=new OtpHandler();
    }
    public void createLocker(int id){
        Locker locker =new Locker(id,true);
        freeLocker.put(locker.getId(), locker);
    }
    public void cleanup(){
        long nowTime=System.currentTimeMillis();
        System.out.println("cleanup triggered");
        while(!expiryQueue.isEmpty()&&expiryQueue.peek().getExpiryTime()<=nowTime){
            ExpirationEntry entry=expiryQueue.poll();
            if(entry!=null&&entry.getLocker()!=null){
                Locker locker=entry.getLocker();
                locker.freeLocker();
                freeLocker.put(locker.getId(), locker);
            }
        }
    }
    public void takeParcelFromLocker(User user,Locker locker,String userOtp){
        long currentTime=System.currentTimeMillis();
        if(locker.isLockerExpired(currentTime)){
            cleanup();
            System.out.println("locker expired");
            return;
        }
        String systemOtp = otpMap.get(locker.getId());
        if (otpHandler.validateOtp(systemOtp, userOtp)) {
            locker.freeLocker();
            otpMap.remove(locker.getId());
            freeLocker.put(locker.getId(), locker);
            System.out.println("Item successfully retrieved.");
        } else {
            System.out.println("Invalid OTP!");
        }        
    }
    public Locker bookLocker(User user){
        Locker locker=lockerStrategy.selectLocker();
        locker.bookLocker(user, System.currentTimeMillis());
        freeLocker.remove(locker.getId());
        String otp = otpHandler.generateOtp();
        otpMap.put(locker.getId(), otp);
        System.out.println("OTP for Locker " + locker.getId() + ": " + otp);

        return locker;    
    }
}
