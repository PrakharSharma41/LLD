package com.example.triefilesystem;

public class FileSystem {
    TrieNodeManager nodeManager;
    FileSystem(){
        this.nodeManager=new TrieNodeManager();
    }
    public boolean createPath(String path,int value){
        return nodeManager.insert(path, value);
    }
    public int get(String path){
        return nodeManager.search(path);
    }
}
