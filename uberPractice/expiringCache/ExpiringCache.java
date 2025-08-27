import java.security.KeyStore.Entry;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

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
    HashMap<String,Entry>cache;
    Deque<Entry>deque;
    int count=0;
    int sum=0;
    ExpiringCache(long ttlMillis){
        deque=new LinkedList<>();
        cache=new HashMap<>();
        this.ttlMillis=ttlMillis;
    }
    void put(String key,int value,long time){
        long expiryTime=time+ttlMillis;
        Entry entry=new Entry(key, value, expiryTime);
        if(cache.containsKey(key)){
            Entry old=cache.get(key);
            sum-=old.value;
            count--;
        }
        cache.put(key, entry);
        deque.add(entry);
        count++;sum+=value;
    }  

    private void evictExpired(long now) {
        while (!deque.isEmpty() && deque.peekFirst().ttl <= now) {
            Entry expired = deque.pollFirst();
            // only remove if it's the latest for the key
            Entry latest = cache.get(expired.key);
            if (latest != null && latest.ttl == expired.ttl) {
                cache.remove(expired.key);
                sum -= expired.value;
                count--;
            }
        }
    }    
    Integer get(String key,long time){
        evictExpired(time);
        Entry entry=cache.get(key);
        if(entry!=null&&entry.ttl>=time)return entry.value;
        return null;
    }
    public Double getAverage(long timestamp) {
        evictExpired(timestamp);

        if (count == 0) return null;
        return (double) sum / count;
    }

}