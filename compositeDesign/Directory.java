package compositeDesign;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystem {

    String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String name,SystemType systemType){
        this.systemType=systemType;
        this.directoryName = name;
        fileSystemList = new ArrayList<>();
        
    }

    public void add(FileSystem fileSystemObj) {
        fileSystemList.add(fileSystemObj);
    }

    public void ls(){
        System.out.println("Directory name " + directoryName);

        for(FileSystem fileSystemObj : fileSystemList){
            fileSystemObj.ls();
        }
    }
}

