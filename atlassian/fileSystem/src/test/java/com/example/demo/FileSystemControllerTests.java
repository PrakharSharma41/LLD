package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.example.demo.entities.Collection;

public class FileSystemControllerTests {

    FileSystemController fileSystemController;

    @BeforeEach
    void execute(){
        fileSystemController=new FileSystemController();
    }
    @Test
    public void testAddFileToSingleCollection() {
        fileSystemController.addFile("doc1.txt", 100, Collections.singletonList("collectionA"));

        assertEquals(100, fileSystemController.getTotalCollectionSize());

        Collection collection = fileSystemController.getCollection("collectionA");
        assertNotNull(collection);
        assertEquals(1, collection.getFiles().size());
        assertEquals("doc1.txt", collection.getFiles().get(0).getName());
    }

    @Test
    public void testAddFileToMultipleCollections() {
        List<String> collections = Arrays.asList("collectionA", "collectionB");
        fileSystemController.addFile("image.jpg", 200, collections);

        assertEquals(200, fileSystemController.getTotalCollectionSize());

        for (String collectionName : collections) {
            Collection collection = fileSystemController.getCollection(collectionName);
            assertNotNull(collection);
            assertEquals(1, collection.getFiles().size());
            assertEquals("image.jpg", collection.getFiles().get(0).getName());
        }
    }

    @Test
    public void testAddFileWhenCollectionIsNull() {
        fileSystemController.addFile("nullfile.txt", 50, null);

        assertEquals(50, fileSystemController.getTotalCollectionSize());
        assertTrue(fileSystemController.collectionNameMap.isEmpty());
        assertTrue(fileSystemController.collectionsSet.isEmpty());
    }    

    @Test
    public void testMultipleFilesToSameCollection() {
        fileSystemController.addFile("file1", 100, Collections.singletonList("sharedCollection"));
        fileSystemController.addFile("file2", 200, Collections.singletonList("sharedCollection"));

        assertEquals(300, fileSystemController.getTotalCollectionSize());

        Collection collection = fileSystemController.getCollection("sharedCollection");
        assertNotNull(collection);
        assertEquals(2, collection.getFiles().size());
    }    

    @Test
    public void testgetTopKCollections(){
        fileSystemController.addFile("file1", 100, Collections.singletonList("Alpha"));      // Alpha: 1 file
        fileSystemController.addFile("file2", 200, Collections.singletonList("Beta"));       // Beta: 1 file
        fileSystemController.addFile("file3", 300, Collections.singletonList("Beta"));       // Beta: 2 files
        fileSystemController.addFile("file4", 400, Collections.singletonList("Gamma"));      // Gamma: 1 file
        fileSystemController.addFile("file5", 500, Collections.singletonList("Beta"));       // Beta: 3 files
        fileSystemController.addFile("file6", 600, Collections.singletonList("Alpha"));      // Alpha: 2 files

        // Expected: Beta (3 files), Alpha (2 files)
        List<Collection> top2 = fileSystemController.getTopKCollections(2);

        assertEquals(2, top2.size());
        assertEquals("Beta", top2.get(0).getName());
        assertEquals("Alpha", top2.get(1).getName());        
    }
}
