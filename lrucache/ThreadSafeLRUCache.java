package lrucache;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.*;

public class ThreadSafeLRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoubleLinkedList<K, V> dll;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public ThreadSafeLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoubleLinkedList<>();
    }

    public V get(K key) {
        rwLock.readLock().lock();
        try {
            Node<K, V> node = map.get(key);
            if (node == null) return null;
            // Upgrade to write lock to move node to front
        } finally {
            rwLock.readLock().unlock();
        }

        // We need write lock to modify list
        rwLock.writeLock().lock();
        try {
            Node<K, V> node = map.get(key);
            if (node == null) return null; // recheck (double-checked locking)
            dll.moveToFront(node);
            return node.value;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void put(K key, V value) {
        rwLock.writeLock().lock();
        try {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                dll.moveToFront(node);
            } else {
                if (map.size() == capacity) {
                    Node<K, V> last = dll.removeLast();
                    if (last != null) map.remove(last.key);
                }
                Node<K, V> newNode = new Node<>(key, value);
                map.put(key, newNode);
                dll.addFirst(newNode);
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
