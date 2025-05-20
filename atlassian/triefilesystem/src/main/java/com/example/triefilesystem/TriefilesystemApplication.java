package com.example.triefilesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriefilesystemApplication {

	public static void main(String[] args) {
		FileSystem fileSystem=new FileSystem();
		System.out.println(fileSystem.createPath("/a", 1));
		System.out.println(fileSystem.createPath("/a/b", 2));
		System.out.println(fileSystem.createPath("/a/bd", 2));
		System.out.println(fileSystem.createPath("/a/b/c", 20));
		System.out.println(fileSystem.get("/a/b/c"));
	}

}
