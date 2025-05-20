public class CacheNode<K,V> {
    K key;
    V value;
    CacheNode<K,V> before;
    CacheNode<K,V>after;
    private long creationTime;
    private long ttlInMillis;
    public CacheNode(K key, V value, long ttlInMillis) {
        this.key = key;
        this.value = value;
        this.ttlInMillis = ttlInMillis;
        this.creationTime = System.currentTimeMillis();
    }
    public boolean isExpired(ExpiryPolicy policy) {
        return policy.isExpired(creationTime, ttlInMillis);
    }    
    public K getKey() {
        return key;
    }
    public long getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
    public long getTtlInMillis() {
        return ttlInMillis;
    }
    public void setTtlInMillis(long ttlInMillis) {
        this.ttlInMillis = ttlInMillis;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public CacheNode<K, V> getBefore() {
        return before;
    }
    public void setBefore(CacheNode<K, V> before) {
        this.before = before;
    }
    public CacheNode<K, V> getAfter() {
        return after;
    }
    public void setAfter(CacheNode<K, V> after) {
        this.after = after;
    }
    
}
