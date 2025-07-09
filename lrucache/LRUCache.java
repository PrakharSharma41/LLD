import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private int capacity;
    DoubleLinkedList<K,V>dll;
    Map<K,Node<K,V>>map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dll=new DoubleLinkedList<K,V>();
        map=new HashMap<>();
    }
    public V get(K key){
        if(map.containsKey(key)==false)return null;
        Node<K,V> node=map.get(key);
        dll.moveToFront(node);
        return node.value;
    }
    public synchronized void put(K key, V value){
        if(map.containsKey(key)){
            Node<K,V> node=map.get(key);
            node.value=value;
            dll.moveToFront(node);
        }else{
            if(map.size()==capacity){
                Node<K,V> last=dll.removeLast();
                if(last!=null)map.remove(last.key);
            }
            Node<K,V>node=new Node<K,V>(key, value);
            map.put(key, node);
            dll.addFirst(node);
        }
    }
    
}
