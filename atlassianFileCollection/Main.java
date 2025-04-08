import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem=new FileSystem();
        fileSystem.addFile("f1", 100, Arrays.asList("c1","c2","c3"));
        fileSystem.addFile("f2", 100, Arrays.asList("c2","c3"));
        fileSystem.addFile("f3", 100, Arrays.asList());
        fileSystem.addFile("f4", 100, Arrays.asList("c3"));

        fileSystem.getTopKCollections(2);        
    }
}
