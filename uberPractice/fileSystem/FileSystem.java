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
        Object lock;
        Directory(String name,Directory parent){
            this.name=name;this.parent=parent;
            children=new HashMap<>();
            lock=new Object();
        }
        Directory getChild(String name) {
            // synchronized (lock) {
                return children.get(name);
            // }
        }       
        void addChild(Directory dir){
            // synchronized(lock){
                children.put(dir.name, dir);
            // }
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
        Directory dir=path.startsWith("/")?root:getCurrent();
        for(String p: parts){
            if(p.isEmpty())continue;
            Directory next;
            synchronized (dir.lock) {
                next = dir.getChild(p);
                if (next == null) {
                    next = new Directory(p, dir);
                    dir.addChild(next);
                }
            }
            dir = next;
        }
    }
    public void cd(String path){
        String[] parts=path.split("/");
        Directory dir=path.startsWith("/")?root:getCurrent();
        for(String p:parts){
            if(p.isEmpty())continue;
            if(p.equals("."))continue;
            if(p.equals("..")){                
                synchronized (dir.lock) {
                    if (dir.parent != null) {
                        dir = dir.parent;
                    }
                }
                continue;
            }
            try{
                Pattern compiledPattern=Pattern.compile(p);
                // Directory match=dir.getChild(p);
                Directory match=null;
                synchronized (dir.lock) {
                    for (Directory child : dir.children.values()) {
                        if (compiledPattern.matcher(child.name).matches()) {
                            match = child;
                            break;
                        }
                    }
                }
                if(match==null){
                    System.out.println(p+" not present");break;
                }
                dir=match;
            }catch(Exception e){
                System.out.println(e);
            }
        }
        setCurrent(dir);
        System.out.println("current directory is "+dir);
    }
    public String pwd(){
        if(root==current)return "/";
        List<String> path = new ArrayList<>();
        Directory node = getCurrent();
        while (node != null && node != root) {
            path.add(node.name);
            node = node.parent;
        }
        Collections.reverse(path);
        System.out.println("/" + String.join("/", path));
        return "/" + String.join("/", path);
    }
    private Directory getCurrent() {
        synchronized (current.lock) {
            return current;
        }
    }
    private void setCurrent(Directory dir) {
        synchronized (current.lock) {
            current = dir;
        }
    }    
}
