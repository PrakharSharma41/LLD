import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiringCache<K,V> {
    ConcurrentHashMap<K,CacheEntry<V>>cache;
    PriorityBlockingQueue<ExpirationEntry<K>>expirationEntry;
    Thread cleanup;
    // ScheduledExecutorService worker=Executors.newSingleThreadScheduledExecutor();
    ExpiringCache(){
        cleanup=new Thread(()->{
            try{
                Thread.sleep(2000);
                cleanup();
            }catch(Exception e){

            }
        });
        cleanup.start();
        // worker.scheduleAtFixedRate(this::cleanup,2000,2000,TimeUnit.MILLISECONDS);
        cache=new ConcurrentHashMap<>();
        expirationEntry=new PriorityBlockingQueue<>();
    }
    private void cleanup() {
        long now = System.currentTimeMillis();
        while (!expirationEntry.isEmpty() && expirationEntry.peek().expiryTime <= now) {
            ExpirationEntry<K> expiredEntry = expirationEntry.poll(); // Thread-safe operation
            if (expiredEntry != null) {
                cache.remove(expiredEntry.key); // Remove from the cache
            }
        }
    }    
    class CacheEntry<V>{
        long expiryTime;
        V value;
        public CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
        @Override
        public String toString(){
            return expiryTime+" "+value;
        }
    }
    class ExpirationEntry<K> implements Comparable<ExpirationEntry<K>>{
        long expiryTime;
        K key;
        public ExpirationEntry(K key,long expiryTime){
            this.expiryTime=expiryTime;
            this.key=key;
        }
        public int compareTo(ExpirationEntry<K> other){
            return Long.compare(this.expiryTime, other.expiryTime);
        }
    }
    public V get(K key){
        long now=System.currentTimeMillis();
        while(!expirationEntry.isEmpty()&&expirationEntry.peek().expiryTime<now){
            expirationEntry.poll();
        }
        CacheEntry<V> value=cache.get(key);
        return value.value;
    }
    public void put(K key, V value, long ttlInMillis) {
        long expiryTime = System.currentTimeMillis() + ttlInMillis;
        CacheEntry<V> newEntry = new CacheEntry<>(value, expiryTime);
        cache.put(key, newEntry);
        expirationEntry.offer(new ExpirationEntry<>(key, expiryTime)); // No additional lock required
    }
    public static void main(String[] args) {
        ExpiringCache<String,String>cache=new ExpiringCache<>();
        cache.put("key","value",10000);
        System.out.println(cache.get("key"));
    }
    
}
