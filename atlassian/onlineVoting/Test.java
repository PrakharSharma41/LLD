import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test{


    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();
        List<List<String>> ballots = Arrays.asList(
            Arrays.asList("A", "B", "C"),
            Arrays.asList("B", "C", "A"),
            Arrays.asList("C", "A", "B")
        );

        List<String> results = system.getResults(ballots);
        System.out.println(results);
    }    
}
// A 7
// B 6
// C 5