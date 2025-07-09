public class DoubleLinkedList<K,V> {
    private Node<K,V>head;
    private Node<K,V>tail;
    DoubleLinkedList(){
        head=new Node<K,V>(null,null);
        tail=new Node<K,V>(null,null);
        head.next=tail;
        tail.prev=head;
    }
    public void addFirst(Node<K, V> node){
        Node<K,V>afterHead=head.next;
        head.next=node;node.prev=head;
        node.next=afterHead;afterHead.prev=node;
    }
    public void remove(Node<K, V> node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    public void moveToFront(Node<K, V> node) {
        remove(node);
        addFirst(node);
    }
    public Node<K, V> removeLast() {
        if (tail.prev == head) return null;
        Node<K, V> last = tail.prev;
        remove(last);
        return last;
    }        
}
