package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.example.demo.entities.Collection;
import com.example.demo.entities.File;

public class FileSystemController {
    int totalCollectionSize=0;
    TreeSet<Collection>collectionsSet;
    HashMap<String,Collection>collectionNameMap;
    FileSystemController(){
        this.totalCollectionSize=0;
        collectionsSet=new TreeSet<>((Collection a,Collection b)->{
            if(a.getTotalSize()!=b.getTotalSize())
            return b.getTotalSize()-a.getTotalSize();
            return b.getName().compareTo(a.getName());
        });
        collectionNameMap=new HashMap<>();
    }
    public void addFile(String fileName,int fileSize,List<String>collectionNames){
        totalCollectionSize+=fileSize;
        File file=new File(fileSize, fileName);
        if(collectionNames==null || collectionNames.size()==0)return ;
        for(String collectionName:collectionNames){
            Collection collection=collectionNameMap.getOrDefault(collectionName, new Collection(collectionName));
            if(collectionsSet.contains(collection)){
                collectionsSet.remove(collection);
            }
            collection.addFile(file);
            collectionsSet.add(collection);
            collectionNameMap.put(collectionName, collection);
        }
    }
    public List<Collection> getTopKCollections(int k) {
        return collectionNameMap.values().stream()
            .sorted((a, b) -> {
                int cmp = Integer.compare(b.getFiles().size(), a.getFiles().size());
                if (cmp == 0) {
                    return a.getName().compareTo(b.getName()); // optional: tie-breaker
                }
                return cmp;
            })
            .limit(k)
            .collect(Collectors.toList());
    }  
    public List<Collection> getTopKCollections1(int k) {
        List<Collection>collections=new ArrayList<>();
        for(Collection collection:collectionsSet){
            collections.add(collection);
            if(collections.size()==k)break;
        }
        return collections;
    }  

    public int getTotalCollectionSize(){
        return totalCollectionSize;
    }
    public Collection getCollection(String collectionName){
        return collectionNameMap.get(collectionName);
    }
}
