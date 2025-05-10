package com.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.example.demo.entities.Collection;
import com.example.demo.entities.File;
import com.example.demo.entities.FileSystemAttributes;

public class ThreadSafeController {
    int totalCollectionSize=0;
    TreeSet<FileSystemAttributes>collectionsSet;
    HashMap<String,FileSystemAttributes>nameMap;
    ThreadSafeController(){
        this.totalCollectionSize=0;
        collectionsSet=new TreeSet<>((FileSystemAttributes a,FileSystemAttributes b)->{
            if(a.getSize()!=b.getSize())
            return b.getSize()-a.getSize();
            return b.getName().compareTo(a.getName());
        });
        nameMap=new HashMap<>();
    }
    public synchronized void addFile(String fileName,int fileSize,List<String>collectionNames){
        totalCollectionSize+=fileSize;
        File file=new File(fileSize, fileName);
        if(collectionNames==null || collectionNames.size()==0)return ;
        for(String collectionName:collectionNames){
            Collection collection=(Collection)nameMap.getOrDefault(collectionName, new Collection(collectionName));
            if(collectionsSet.contains(collection)){
                collectionsSet.remove(collection);
            }
            collection.addFile(file);
            collectionsSet.add(collection);
            nameMap.put(collectionName, collection);
        }
    }
    public List<FileSystemAttributes> getTopKCollections1(int k) {
        List<FileSystemAttributes>collections=new ArrayList<>();
        synchronized(collectionsSet){
            for(FileSystemAttributes collection:collectionsSet){
                collections.add(collection);
                if(collections.size()==k)break;
            }
            return collections;    
        }
    }  

    public int getTotalCollectionSize(){
        return totalCollectionSize;
    }
    public FileSystemAttributes getCollection(String collectionName){
        return nameMap.get(collectionName);
    }
}
