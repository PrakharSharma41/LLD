import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FileSystem {
    int totalCollectionSize=0;
    TreeSet<Collection>collectionsSet;
    HashMap<String,Collection>collectionNameMap;
    FileSystem(){
        this.totalCollectionSize=0;
        collectionsSet=new TreeSet<>((Collection a,Collection b)->{
            if(a.getTotalSize()!=b.getTotalSize())
            return b.getTotalSize()-a.getTotalSize();
            return b.getName().compareTo(a.getName());
        });
        collectionNameMap=new HashMap<>();
    }
    public void addFile(String fileName,int fileSize,List<String>collectionNames){
        totalCollectionSize+=fileSize;
        File file=new File(fileSize, fileName);
        for(String collectionName:collectionNames){
            Collection collection=collectionNameMap.getOrDefault(collectionName, new Collection(collectionName));
            collection.addFile(file);
            if(collectionsSet.contains(collection)){
                collectionsSet.remove(collection);
            }
            collectionsSet.add(collection);
            collectionNameMap.put(collectionName, collection);
        }
    }
    public void getTopKCollections(int k){
        int i=0;
        for(Collection collection:collectionsSet){
            System.out.println("collection is "+collection);
            if(i==k)break;
            i++;
        }
    }
}
