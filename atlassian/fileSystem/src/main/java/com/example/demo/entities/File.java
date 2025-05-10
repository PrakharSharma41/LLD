package com.example.demo.entities;

public class File implements FileSystemAttributes{
    int fileSize;
    String name;
    public File(int fileSize,String name){
        this.fileSize=fileSize;this.name=name;
    }
    public int getSize() {
        return fileSize;
    }
    public void setSize(int fileSize) {
        this.fileSize = fileSize;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
