package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.demo.entities.Collection;
import com.example.demo.entities.File;
import com.example.demo.entities.FileSystemAttributes;

public class ThreadSafeControllerWithLock {
    SortedSet<FileSystemAttributes> collectionsSet;
    Map<String, FileSystemAttributes> nameMap;
    Map<String, Lock> collectionLocks;  // Lock for each collection
    AtomicInteger totalCollectionSize;

    ThreadSafeControllerWithLock() {
        totalCollectionSize = new AtomicInteger(0);
        collectionsSet = Collections.synchronizedSortedSet(new TreeSet<>((FileSystemAttributes a, FileSystemAttributes b) -> {
            if (a.getSize() != b.getSize()) return b.getSize() - a.getSize();
            return b.getName().compareTo(a.getName());
        }));
        nameMap = new ConcurrentHashMap<>();
        collectionLocks = new ConcurrentHashMap<>(); // To store locks for each collection
    }

    private Lock getLockForCollection(String collectionName) {
        // If lock for the collection doesn't exist, create a new lock
        return collectionLocks.computeIfAbsent(collectionName, k -> new ReentrantLock());
    }

    public void addFile(String fileName, int fileSize, List<String> collectionNames) {
        totalCollectionSize.addAndGet(fileSize);
        File file = new File(fileSize, fileName);
        if (collectionNames == null || collectionNames.isEmpty()) return;

        for (String collectionName : collectionNames) {
            // Get or create the collection
            Collection collection = (Collection)nameMap.computeIfAbsent(collectionName, name -> new Collection(name));

            // Get the lock for the collection and acquire it
            Lock lock = getLockForCollection(collectionName);
            lock.lock();  // Acquire the lock for the collection
            try {
                if (collectionsSet.contains(collection)) {
                    collectionsSet.remove(collection);
                }
                collection.addFile(file);
                collectionsSet.add(collection);
            } finally {
                lock.unlock();  // Release the lock
            }

            // Put the collection in the map after modification
            nameMap.put(collectionName, collection);
        }
    }

    public List<FileSystemAttributes> getTopKCollections1(int k) {
        List<FileSystemAttributes> collections = new ArrayList<>();
        // Synchronize the access to the sorted set to avoid issues when modifying
        synchronized (collectionsSet) {
            for (FileSystemAttributes collection : collectionsSet) {
                collections.add(collection);
                if (collections.size() == k) break;
            }
        }
        return collections;
    }

    public int getTotalCollectionSize() {
        return totalCollectionSize.get();
    }

    public FileSystemAttributes getCollection(String collectionName) {
        return nameMap.get(collectionName);
    }
}
