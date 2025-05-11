
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class NodeManager {
    Node root;
    HashMap<String,List<Node>>paths;
    NodeManager(Node root){
        this.root=root;
        paths=new HashMap<>();
        createParents(root,new ArrayList<>());
    }

    public String findClosestParent(List<String>inputs){
        if(inputs==null ||inputs.size()==0|| root==null){
            return "";
        }
        List<List<Node>>parentPaths=new ArrayList<>();
        int minLength=Integer.MAX_VALUE;
        for(String input: inputs){
            List<Node> path = paths.get(input);
            if (path == null) {
                return "no common parent";
            }
            parentPaths.add(path);
            minLength = Math.min(minLength, path.size());
        }
        String closestParent="";
        for(int i=0;i<minLength;i++){
            String current = parentPaths.get(0).get(i).value;
            boolean allMatch=true;
            for(int j=1;j<parentPaths.size();j++){
                if (!current.equals(parentPaths.get(j).get(i).value)) {
                    allMatch = false;
                    break;
                }                
            }
            if(allMatch)closestParent=current;
            else break;
        }
        return closestParent;   
    }
    public void createParents(Node head,List<Node>path){
        if(head==null)return ;
        path.add(head);
        paths.put(head.value, new ArrayList<>(path));
        if(head.children!=null){
            for(Node node: head.children){
                createParents(node, path);
            }    
        }
        path.remove(path.size()-1);
    }
}
