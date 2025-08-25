package uberPractice.fileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class FileSystem {
    class Directory{
        String name;
        @Override
        public String toString() {
            return "Directory [name=" + name + ", parent=" + parent + "]";
        }
        Directory parent;
        public Map<String,Directory>children;
        Directory(String name,Directory parent){
            this.name=name;this.parent=parent;
            children=new HashMap<>();
        }
        Directory getChild(String path){
            return children.get(path);
        }
        void addChild(Directory dir){
            children.put(dir.name, dir);
        }
    }    
    Directory root;
    Directory current;
    FileSystem(){
        root=new Directory("/", null);
        current=root;
    }
    public void mkdir(String path){
        String[]parts=path.split("/");
        Directory dir=path.startsWith("/")?root:current;
        for(String p: parts){
            if(p.isEmpty())continue;
            Directory d1=dir.getChild(p);
            if(d1==null){
                d1=new Directory(p, dir);                
                dir.addChild(d1);
            }
            dir=d1;
        }
    }
    public void cd(String path){
        String[] parts=path.split("/");
        Directory dir=path.startsWith("/")?root:current;
        for(String p:parts){
            if(p.isEmpty())continue;
            if(p.equals("."))continue;
            if(p.equals("..")){
                if(dir.parent!=null)dir=dir.parent;
            }
            Pattern compiledPattern=Pattern.compile(p);
            Directory match=dir.getChild(p);
            for(Directory d:dir.children.values()){
                if(compiledPattern.matcher(d.name).find()){
                    match=d;break;
                }
            }
            if(match==null){
                System.out.println(p+" not present");break;
            }
            dir=match;
        }
        current=dir;
        System.out.println("current directory is "+dir);
    }
    public String pwd(){
        if(root==current)return "/";
        List<String> path = new ArrayList<>();
        Directory node = current;
        while (node != null && node != root) {
            path.add(node.name);
            node = node.parent;
        }
        Collections.reverse(path);
        return "/" + String.join("/", path);
    }

}
