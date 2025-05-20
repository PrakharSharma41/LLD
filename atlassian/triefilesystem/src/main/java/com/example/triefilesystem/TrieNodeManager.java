package com.example.triefilesystem;

public class TrieNodeManager {
    TrieNode root;
    TrieNodeManager(){
        root=new TrieNode(-1);
    }
    public boolean insert(String path,int value){
        TrieNode node=root;
        String[] parts=path.split("/");
        for(int i=1;i<parts.length-1;i++){
            String part=parts[i];
            if(!node.contains(part)){
                return false;
            }
            node=node.getChildren(part);
        }
        if(node.contains(parts[parts.length-1])){
            return false;
        }
        node.put(parts[parts.length-1], new TrieNode(value));
        return true;
    }
    public int search(String path){
        TrieNode node=root;
        String[] parts=path.split("/");
        for(int i=1;i<parts.length;i++){
            String part=parts[i];
            if(!node.contains(part)){
                return -1;
            }
            node=node.getChildren(part);
        }
        return node.value;
    }
}
// /
// a 
// b    bd
// c
// /a/b/c/d