class AllOne {

    class Node{
        HashSet<String>stringsCheck;
        int value;
        Node right,left;
        Node(int value){
            this.value=value;
            stringsCheck=new HashSet<>();
        }
    }
    HashMap<String,Node>nodeMap;
    Node head,tail;
    public AllOne() {
        nodeMap=new HashMap<>();
        head=new Node(-100);
        tail=new Node(-100);
        head.right=tail;tail.left=head;
    }
    public void inc(String key) {
        if(nodeMap.containsKey(key)){
            Node node=nodeMap.get(key);
            node.stringsCheck.remove(key);
            if(node.right.value==node.value+1){
                node.right.stringsCheck.add(key);
                nodeMap.put(key,node.right);
            }else{
                Node rightNode=new Node(node.value+1);
                rightNode.stringsCheck.add(key);
                rightNode.right=node.right;
                node.right.left=rightNode;
                node.right=rightNode;
                rightNode.left=node;
                nodeMap.put(key,rightNode);                
            }
            if(node.stringsCheck.isEmpty())removeNode(node);
        }else{
            Node firstNode=head.right;
            if(firstNode.value!=1){
                Node newNode=new Node(1);
                newNode.stringsCheck.add(key);
                newNode.left=head;
                newNode.right=firstNode;
                firstNode.left=newNode;
                head.right=newNode;
                nodeMap.put(key,newNode);
            }else{
                firstNode.stringsCheck.add(key);
                nodeMap.put(key,firstNode);
            }
        }
    }
    public void removeNode(Node node){
        node.left.right=node.right;
        node.right.left=node.left;
    }
    
    public void dec(String key) {
        if(nodeMap.containsKey(key)==false)return ;
        Node node=nodeMap.get(key);
        node.stringsCheck.remove(key);
        int value=node.value;
        if(value==1){
            nodeMap.remove(key);
        }else{
            Node leftNode=node.left;
            if(leftNode.value!=value-1){
                Node newNode=new Node(value-1);
                newNode.stringsCheck.add(key);
                nodeMap.put(key,newNode);
                newNode.left=leftNode;
                newNode.right=node;
                leftNode.right=newNode;
                node.left=newNode;
            }else{
                leftNode.stringsCheck.add(key);
                nodeMap.put(key,leftNode);
            }
        }
        if(node.stringsCheck.isEmpty())removeNode(node);
    }
    
    public String getMaxKey() {
        if(tail.left.stringsCheck.size()==0)return "";
        Iterator<String> itr=tail.left.stringsCheck.iterator();
        if(itr.hasNext())return itr.next();
        return null;
    }
    
    public String getMinKey() {
        if(head.right.stringsCheck.size()==0)return "";
        Iterator<String> itr=head.right.stringsCheck.iterator();
        if(itr.hasNext())return itr.next();
        return null;        
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */