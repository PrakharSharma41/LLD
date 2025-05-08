package DesignPatterns.compositeDesign;

public class File extends FileSystem{
    String fileName;
    public File(String name,SystemType systemType){
        super.systemType=systemType;
        this.fileName = name;
    }

    public void ls(){
        System.out.println("file name " + fileName);
    }
}
