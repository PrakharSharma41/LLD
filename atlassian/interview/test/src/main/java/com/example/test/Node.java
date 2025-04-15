package atlassian.interview.test.src.main.java.com.example.test;

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
    
}
