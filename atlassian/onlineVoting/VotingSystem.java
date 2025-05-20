import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VotingSystem {
    TieBreakStrategy strategy;
    Map<String,int[]>indexVotesMap=new HashMap<>();
    Map<String,Integer>firstReachMap=new HashMap<>();

    VotingSystem(TieBreakStrategy strategy){
        this.strategy=strategy;
    }

    public List<String> getResults(List<List<String>> ballots) {
        Map<String, Integer> points = new HashMap<>();
        for(List<String>b:ballots){
            for(int i=0;i<3;i++){
                String candidate=b.get(i);
                int score=3-i;
                points.put(candidate,points.getOrDefault(candidate, 0)+1);
                if(!firstReachMap.containsKey(candidate)){
                    firstReachMap.put(candidate,points.get(candidate));
                }
                indexVotesMap.putIfAbsent(candidate, new int[3]);
                indexVotesMap.get(candidate)[score]++;
            }
        }
        List<String>ans=new ArrayList<>(points.keySet());
        ans.sort((a,b)->{
            if(points.get(a)!=points.get(b)){
                return points.get(a)-points.get(b);
            }
            return strategy.compare(a, b);
        });        
        return ans;
    }
}
