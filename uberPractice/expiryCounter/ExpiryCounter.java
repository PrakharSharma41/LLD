import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiryCounter{
    Map<String,List<Long>>ttls;
    long ttl;
    ScheduledExecutorService cleaner;
    ExpiryCounter(long ttl){
        this.ttl=ttl;
        ttls=new ConcurrentHashMap<>();
        cleaner = Executors.newSingleThreadScheduledExecutor();
        cleaner.scheduleAtFixedRate(this::cleanup, 
                                    2000, 
                                    2000, 
                                    TimeUnit.MILLISECONDS);        
    }
    public void addCounter(String key){        
        long expiryTime=System.currentTimeMillis()+ttl;
        ttls.compute(key,(k,v)->{
            if(v==null)v=Collections.synchronizedList(new ArrayList<>());
            v.add(expiryTime);
            return v;
        });
        // ttls.computeIfAbsent(key, k->new ArrayList<>()).add(expiryTime);
    }
    public int getCounter(String key){
        int total=0;
        List<Long>expiry=ttls.get(key);
        if(expiry==null||expiry.isEmpty())return 0;
        synchronized(expiry){
            long currentTime=System.currentTimeMillis();     
            int low=0,high=expiry.size()-1,mid,validIndex=expiry.size();
            while(low<=high){
                mid=(low+high)/2;
                if(expiry.get(mid)>=currentTime){
                    validIndex=mid;high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            total=expiry.size()-validIndex;
            // expiry.subList(0, validIndex).clear();  // can be uncommented
            return total;    
        }
    }
    public int getTotalCounter(){
        int totalValid=0;
        for(String k:ttls.keySet()){
            totalValid+=getCounter(k);
        }
        return totalValid;
    }
    private void cleanup() {
        long now = System.currentTimeMillis();
        for (Map.Entry<String, List<Long>> entry : ttls.entrySet()) {
            List<Long> expiry = entry.getValue();
            synchronized(expiry){
                if (expiry.isEmpty()) continue;

                // Binary search for first valid index
                int low = 0, high = expiry.size() - 1, mid, validIndex = expiry.size();
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (expiry.get(mid) >= now) {
                        validIndex = mid;
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
    
                if (validIndex > 0) {
                    expiry.subList(0, validIndex).clear(); // trim expired entries
                }    
            }
        }
    }    
}