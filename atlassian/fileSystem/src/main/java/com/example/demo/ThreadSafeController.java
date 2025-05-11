package com.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.demo.entities.Collection;
import com.example.demo.entities.File;
import com.example.demo.entities.FileSystemAttributes;

public class ThreadSafeController {
    AtomicInteger totalCollectionSize;
    TreeSet<FileSystemAttributes>collectionsSet;
    ConcurrentHashMap<String,FileSystemAttributes>nameMap;
    ThreadSafeController(){
        totalCollectionSize = new AtomicInteger(0);
        collectionsSet=new TreeSet<>((FileSystemAttributes a,FileSystemAttributes b)->{
            if(a.getSize()!=b.getSize())
            return b.getSize()-a.getSize();
            return b.getName().compareTo(a.getName());
        });
        nameMap=new ConcurrentHashMap<>();
    }
    public void addFile(String fileName,int fileSize,List<String>collectionNames){
        totalCollectionSize.addAndGet(fileSize);
        File file=new File(fileSize, fileName);
        if(collectionNames==null || collectionNames.size()==0)return ;
        for(String collectionName:collectionNames){
            Collection collection=(Collection)nameMap.getOrDefault(collectionName, new Collection(collectionName));
            synchronized(collectionsSet){
                if(collectionsSet.contains(collection)){
                    collectionsSet.remove(collection);
                }
                collection.addFile(file);
                collectionsSet.add(collection);    
            }
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

    public synchronized int getTotalCollectionSize(){
        return totalCollectionSize.get();
    }
    public FileSystemAttributes getCollection(String collectionName){
        return nameMap.get(collectionName);
    }
}
