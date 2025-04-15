package atlassian.interview.test.src.main.java.com.example.test;

import java.util.ArrayList;
import java.util.List;

public class NodeManager {
    Node root;
    NodeManager(Node root){
        this.root=root;
    }

    public String findClosestParent(List<String>inputs){

        if(inputs==null ||inputs.size()==0|| root==null){
            return "";
        }
        List<List<String>>parents=new ArrayList<>();
        int n=inputs.size(),minParentSize=Integer.MAX_VALUE;
        boolean inputPresent=true;
        for(int i=0;i<inputs.size();i++){
            String input=inputs.get(i);
            List<String>parent=new ArrayList<>();
            boolean parentFound=listParents(root,input,parent);
            if(parentFound==false){
                inputPresent=false;break;
            }
            minParentSize=Math.min(minParentSize,parent.size());
            parents.add(parent);
        }
        if(inputPresent==false){
            return "no common parent";
        }

        int index=0;
        String closestParent="";
        while(index<minParentSize){
            for(int parentIndex=0;parentIndex<parents.size()-1;parentIndex++){
                String parent1=parents.get(parentIndex).get(index);
                String parent2=parents.get(parentIndex+1).get(index);
                if(parent1.equals(parent2)){
                    closestParent=parent1;
                }else{
                    break;
                }
            }
            index++;
        }
        return closestParent;   
    }
    // company engg be
    // true or false if input is found
    public boolean listParents(Node root,String input,List<String>parents){

        if(root.getValue().equals(input)){
            return true;
        }
        if(root.getChildren()==null){
            return false;
        }

        parents.add(root.getValue());
        boolean inputFound=false;
        for(Node children: root.getChildren()){
            boolean found=listParents(children, input, parents);
            if(found==true){
                inputFound=true;break;
            }
        }
        if(inputFound==false){
            parents.remove(parents.size()-1);
        }
        return inputFound;
    }
}
// class Node{
// 	String value
// 	Node[] children
// }

// mona,alice,lisa

// ans company

// i=0
// parentIndex 0  company,hr
// parentIndex 1  company,engg,be
// parentIndex 2  company,,engg,fe

//parents
// parent: company,hr