import java.security.KeyStore.Entry;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ExpiringCache{
    class Entry{
        String  key;
        int  value;
        long ttl;
        Entry(String key,int value,long ttl){
            this.key=key;
            this.ttl=ttl;
            this.value=value;
        }
    }
    long ttlMillis;
    Map<String,Entry>cache;
    Map<String,ReentrantLock>locks;
    Deque<Entry>deque;
    int count=0;
    int sum=0;
    ReentrantLock globalLock = new ReentrantLock(); // for expiry queue
    ExpiringCache(long ttlMillis){
        deque=new LinkedList<>();
        cache=new ConcurrentHashMap<>();
        locks=new ConcurrentHashMap<>();
        this.ttlMillis=ttlMillis;
    }
    void put(String key,int value,long time){
        long expiryTime=time+ttlMillis;
        ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        try{
            lock.lock();
            Entry entry=new Entry(key, value, expiryTime);
            if(cache.containsKey(key)){
                Entry old=cache.get(key);
                sum-=old.value;
                count--;
            }
            cache.put(key, entry);
            deque.add(entry);
            count++;sum+=value;
    
        }finally{
            lock.unlock();
        }
    }  

    private void evictExpired(long now) {
        while (!deque.isEmpty() && deque.peekFirst().ttl <= now) {
            Entry expired = deque.pollFirst();
            // only remove if it's the latest for the key
            ReentrantLock lock = locks.computeIfAbsent(expired.key, k -> new ReentrantLock());
            try{
                lock.lock();
                Entry latest = cache.get(expired.key);
                // System.out.println(latest.key);
                if (latest != null && latest.ttl == expired.ttl) {
                    cache.remove(expired.key);
                    sum -= expired.value;
                    count--;
                }
            }finally{
                lock.unlock();
            }
        }
    }    
    Integer get(String key,long time){
        ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        // evictExpired(time);
        try{
            lock.lock();
            Entry entry=cache.get(key);
            if(entry==null||entry.ttl<=time)return null;
            return entry.value;
        }finally{
            lock.unlock();
        }
    }
    public Double getAverage(long timestamp) {
        try{
            globalLock.lock();
            evictExpired(timestamp);
            if (count == 0) return null;
            return (double) sum / count;    
        }finally{
            globalLock.unlock();;
        }
    }

}