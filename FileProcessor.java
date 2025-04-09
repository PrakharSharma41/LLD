import java.util.*;
import java.util.HashMap;

public class FileProcessor {
    private long totalSize; // Total size of processed files
    private final Map<String, Long> collectionSizes; // Collection -> Total Size

    public FileProcessor() {
        this.totalSize = 0;
        this.collectionSizes = new HashMap<>();
    }

    public void processFile(String fileName, long fileSize, List<String> collections) {
        totalSize += fileSize; // Update total size

        if (collections != null) {
            for (String collection : collections) {
                collectionSizes.put(collection, collectionSizes.getOrDefault(collection, 0L) + fileSize);
            }
        }
    }

    public long getTotalProcessedSize() {
        return totalSize;
    }

    public List<Map.Entry<String, Long>> getTopKCollections(int k) {
        PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));
        // PriorityQueue<Map.Entry<String, Long>> maxHeap = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue).reversed());

        for (Map.Entry<String, Long> entry : collectionSizes.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element
            }
        }

        List<Map.Entry<String, Long>> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> Long.compare(b.getValue(), a.getValue())); // Sort descending
        return result;
    }

    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();

        processor.processFile("file1.txt", 500, Arrays.asList("A", "B"));
        processor.processFile("file2.txt", 300, Arrays.asList("A"));
        processor.processFile("file3.txt", 700, Arrays.asList("B", "C"));
        processor.processFile("file4.txt", 400, null); // No collection

        System.out.println("Total Processed Size: " + processor.getTotalProcessedSize());
        System.out.println("Top 2 Collections:");
        
        for (Map.Entry<String, Long> entry : processor.getTopKCollections(2)) {
            System.out.println("Collection: " + entry.getKey() + ", Size: " + entry.getValue());
        }
    }
}
