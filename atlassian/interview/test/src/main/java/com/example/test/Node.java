
public class Node {
    String value;
    Node[] children;
    Node(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Node[] getChildren() {
        return children;
    }
    public void setChildren(Node[] children) {
        this.children = children;
    }
    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }    
    
}
