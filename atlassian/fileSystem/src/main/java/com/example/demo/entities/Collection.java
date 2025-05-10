package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Collection implements FileSystemAttributes{
    List<FileSystemAttributes>files;
    int totalSize;
    String name;
    public Collection(String name) {
        files=new ArrayList<>();
        totalSize=0;
        this.name=name;
    }
    public void addFile(FileSystemAttributes file){
        files.add(file);
        setSize(totalSize+file.getSize());
    }
    public void setSize(int size){
        this.totalSize=size;
    }
    public List<FileSystemAttributes> getFiles() {
        return files;
    }
    public int getSize() {
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
