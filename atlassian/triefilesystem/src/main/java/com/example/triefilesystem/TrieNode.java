package com.example.triefilesystem;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<String,TrieNode>children;
    int value;
    public TrieNode(int value) {
        this.value = value;
        children=new HashMap<>();
    }
    public boolean contains(String path){
        return children.containsKey(path);
    }
    public void put(String path,TrieNode node){
        children.put(path, node);
    }
    public TrieNode getChildren(String path){
        return children.get(path);
    }
    public int getValue(){
        return value;
    }

}
