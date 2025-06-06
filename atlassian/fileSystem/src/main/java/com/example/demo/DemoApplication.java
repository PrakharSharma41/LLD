package com.example.demo;

import java.nio.file.FileSystem;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Collection;
import com.example.demo.entities.FileSystemAttributes;

@SpringBootApplication
public class DemoApplication {
        public static void main(String[] args) {
                FileSystemController fileSystem=new FileSystemController();
                fileSystem.addFile("f1", 100, Arrays.asList("c1","c2","c3"));
                fileSystem.addFile("f2", 100, Arrays.asList("c2","c3"));
                fileSystem.addFile("f5", 100, Arrays.asList("c2"));
                fileSystem.addFile("f3", 2100, Arrays.asList("c1"));
                fileSystem.addFile("f4", 100, Arrays.asList("c3"));


                List<FileSystemAttributes>collections=fileSystem.getTopKCollections1(3);       
                for(FileSystemAttributes collection:collections){
                        System.out.println(collection);
                }
        }

}
