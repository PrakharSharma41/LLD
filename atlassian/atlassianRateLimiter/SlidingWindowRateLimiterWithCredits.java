package atlassian.atlassianRateLimiter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowRateLimiterWithCredits implements RateLimiter{
    private int maxRequests;
    private int windowSizeInMillis;
    ConcurrentHashMap<String,Deque<Long>>userRequests;
    ConcurrentHashMap<String,Integer>clientCredits;
    int maxCredits;
    private ConcurrentHashMap<String, ReentrantLock> clientLocks;
    public SlidingWindowRateLimiterWithCredits(int maxRequests, int windowSizeInMillis,int maxCredits) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        userRequests=new ConcurrentHashMap<>();
        this.maxCredits=maxCredits;
        clientCredits=new ConcurrentHashMap<>();
        clientLocks=new ConcurrentHashMap<>();
    }
    public boolean allowRequest(String userId){

        clientLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        ReentrantLock lock = clientLocks.get(userId);
        try{
            lock.lock();
            userRequests.putIfAbsent(userId, new LinkedList<>());
            clientCredits.putIfAbsent(userId, maxRequests);            
            Deque<Long>timeStamps=userRequests.get(userId);
            long now=System.currentTimeMillis();
            while(!timeStamps.isEmpty()&&now-windowSizeInMillis>timeStamps.peekFirst()){
                timeStamps.pollFirst();
            }
            int requestCount=timeStamps.size();
            int creditsToAdd=maxRequests-requestCount;
            addCredits(clientCredits, creditsToAdd, maxCredits, userId);
            int remainCredits=clientCredits.get(userId);
            if(requestCount<maxRequests||remainCredits>0){
                timeStamps.add(now);
                clientCredits.put(userId, remainCredits-1);
                System.out.println(userId+" allowed");
                return true;
            }else{
                System.out.println(userId+" blocked");
                return false;
            }            
        }catch(Exception e){
        }finally{
            lock.unlock();
        }
        return false;

    }
    public void addCredits(ConcurrentHashMap<String,Integer>clientCredits,int creditsToAdd,int maxCredits,String userId){
        clientCredits.put(userId, Math.min(maxCredits,clientCredits.getOrDefault(userId, 0)+creditsToAdd));
        // clientCredits.compute(userId, (k, v) -> Math.min(maxCredits, (v == null ? 0 : v + creditsToAdd)));
    }
    public void resetCredits(){
        for(String user:clientCredits.keySet()){
            clientCredits.put(user, 0);
        }
    }
}
