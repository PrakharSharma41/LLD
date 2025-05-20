import java.util.HashMap;
import java.util.Map;

public class Cache<K,V>{
    int maxCapacity;
    int currentCapacity;
    Map<K,CacheNode<K,V>>hashMap;    
    private ExpiryPolicy expiryPolicy;
    private long ttlInMillis; // Default TTL for all nodes
    CacheNode<K,V>head,tail;
    Cache(int capacity,long ttlInMillis, ExpiryPolicy policy){
        this.maxCapacity=capacity;
        this.ttlInMillis = ttlInMillis;
        this.expiryPolicy = policy;
        this.currentCapacity=0;
        hashMap=new HashMap<>();

        head=new CacheNode<K,V>(null,null,0);
        tail=new CacheNode<K,V>(null,null,0);

        head.after=tail;
        tail.before=head;
    }    
    public V get(K key){
        if(hashMap.containsKey(key)==false){
            return null;
        }
        CacheNode<K, V> node = hashMap.get(key);
        if (node.isExpired(expiryPolicy)) {
            removeNode(node);
            hashMap.remove(key);
            currentCapacity--;
            return null;
        }
        removeNode(node);
        insertFirst(node);
        return node.getValue();
    }
    public void insertFirst(CacheNode<K,V>node){
        node.after=head.after;
        head.after.before=node;
        node.before=head;
        head.after=node;
    }
    public void removeNode(CacheNode<K,V>node){
        node.before.after=node.after;
        node.after.before=node.before;
    }

    public void put(K key,V value){
        if(hashMap.containsKey(key)){
            moveNewNode(key, value);
        }else{
            if(currentCapacity==maxCapacity){
                CacheNode<K,V>lastNode=tail.before;
                hashMap.remove(lastNode.getKey());
                removeNode(lastNode);        
            }else{
                currentCapacity++;
            }
            CacheNode<K,V>newNode=new CacheNode<K,V>(key, value,ttlInMillis);
            hashMap.put(key, newNode);
            insertFirst(newNode);
        }
        
    }
    public void moveNewNode(K key, V value){
        CacheNode<K,V> node=hashMap.get(key);
        node.setValue(value);
        node.setCreationTime(System.currentTimeMillis());
        removeNode(node);
        insertFirst(node);

    }
}