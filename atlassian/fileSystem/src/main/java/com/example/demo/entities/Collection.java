package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Collection implements FileSystemAttributes{
    List<File>files;
    int totalSize;
    String name;
    public Collection(String name) {
        files=new ArrayList<>();
        totalSize=0;
        this.name=name;
    }
    public void addFile(File file){
        files.add(file);
        updateSize(totalSize+file.getFileSize());
    }
    public void updateSize(int size){
        this.totalSize=size;
    }
    public List<File> getFiles() {
        return files;
    }
    public int getTotalSize() {
        return totalSize;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Collection [totalSize=" + totalSize + ", name=" + name + "]";
    }
}
