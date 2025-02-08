package TestLLD;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiringCache<K,V> {
    private ConcurrentHashMap<K,CacheEntry<V>>cache=new ConcurrentHashMap<>();
    private PriorityBlockingQueue<ExpirationEntry<K>>expiryQueue=new PriorityBlockingQueue<>();
    private final ScheduledExecutorService cleanupSchedler=Executors.newSingleThreadScheduledExecutor();
    public ExpiringCache(){
        cleanupSchedler.scheduleAtFixedRate(this::cleanup, 1000, 1000, TimeUnit.MILLISECONDS);
    }
    public void put(K key, V value, long ttl){
        long expiryTime=System.currentTimeMillis()+ttl;
        cache.put(key,new CacheEntry<V>(value, expiryTime));
        expiryQueue.put(new ExpirationEntry<K>(key, expiryTime));
    }
    static class CacheEntry<V>{
        private V value;
        private long expiryTime;
        public CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }
    static class ExpirationEntry<K> implements Comparable<ExpirationEntry<K>>{
        private K key;
        private long expiryTime;
        public ExpirationEntry(K key, long expiryTime) {
            this.key = key;
            this.expiryTime = expiryTime;
        }
        @Override
        public int compareTo(ExpirationEntry<K> o) {
            return Long.compare(this.expiryTime, o.expiryTime);
        }   
    }
    public V get(K key){
        CacheEntry<V>entry=cache.get(key);
        if(entry==null){
            return null;
        }
        if(System.currentTimeMillis()>entry.expiryTime){
            cache.remove(key);
            return null;
        }
        return entry.value;
    }
    void cleanup(){
        long now=System.currentTimeMillis();
        System.out.println("running cleanup");
        while(!expiryQueue.isEmpty()&&now>=expiryQueue.peek().expiryTime){
            ExpirationEntry<K>entry=expiryQueue.poll();
            if(entry!=null){
                cache.remove(entry.key);
            }
        }
    }
    void shutdown(){
        cleanupSchedler.shutdown();
    }
    public static void main(String[] args) {
        ExpiringCache<String,String>cache=new ExpiringCache<>();
        cache.put("key1", "value1", 2000);
        System.out.println("Cache content after all threads finished:");
        for (Map.Entry<String, CacheEntry<String>> entry : cache.cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().value);
        }

        // Wait for 5 seconds to let entries expire
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the cache content after entries expire
        System.out.println("Cache content after expiration:");
        for (Map.Entry<String, CacheEntry<String>> entry : cache.cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().value);
        }     
        cache.shutdown();
    }
}
