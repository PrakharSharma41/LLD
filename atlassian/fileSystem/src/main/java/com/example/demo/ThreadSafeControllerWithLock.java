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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.demo.entities.Collection;
import com.example.demo.entities.File;

public class ThreadSafeControllerWithLock {
    SortedSet<Collection> collectionsSet;
    HashMap<String, Collection> collectionNameMap;
    Map<String, Lock> collectionLocks;  // Lock for each collection
    AtomicInteger totalCollectionSize;

    ThreadSafeControllerWithLock() {
        totalCollectionSize = new AtomicInteger(0);
        collectionsSet = new TreeSet<>((Collection a, Collection b) -> {
            if (a.getTotalSize() != b.getTotalSize())
                return b.getTotalSize() - a.getTotalSize();
            return b.getName().compareTo(a.getName());
        });
        collectionNameMap = new HashMap<>();
        collectionLocks = new HashMap<>(); // To store locks for each collection
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
            Collection collection = collectionNameMap.computeIfAbsent(collectionName, name -> new Collection(name));

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
            collectionNameMap.put(collectionName, collection);
        }
    }

    public List<Collection> getTopKCollections(int k) {
        return collectionNameMap.values().stream()
            .sorted((a, b) -> {
                int cmp = Integer.compare(b.getFiles().size(), a.getFiles().size());
                if (cmp == 0) {
                    return a.getName().compareTo(b.getName()); // optional: tie-breaker
                }
                return cmp;
            })
            .limit(k)
            .collect(Collectors.toList());
    }

    public List<Collection> getTopKCollections1(int k) {
        List<Collection> collections = new ArrayList<>();
        synchronized (collectionsSet) {
            for (Collection collection : collectionsSet) {
                collections.add(collection);
                if (collections.size() == k) break;
            }
        }
        return collections;
    }

    public int getTotalCollectionSize() {
        return totalCollectionSize.get();
    }

    public Collection getCollection(String collectionName) {
        return collectionNameMap.get(collectionName);
    }
}
