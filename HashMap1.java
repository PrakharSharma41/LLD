public class HashMap1 {
    class Entity<K,V>{
        K key;
        V value;
        Entity next;
        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
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
    }
    public Entity[] hashTable;
    
}
