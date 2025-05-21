import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VotingSystem {
    Map<String,int[]>indexVotesMap=new HashMap<>();
    Map<String,Integer>lastBallotMap=new HashMap<>();
    Map<String, Integer> points = new HashMap<>();


    public List<String> getResults(List<List<String>> ballots) {
        for(List<String>b:ballots){
            int candidateSize=b.size();
            for(int i=0;i<candidateSize;i++){
                String candidate=b.get(i);
                int score=candidateSize-i;
                points.put(candidate,points.getOrDefault(candidate, 0)+score);
                indexVotesMap.putIfAbsent(candidate, new int[4]);
                indexVotesMap.get(candidate)[score]++;
            }
        }
        for(int i=0;i<ballots.get(ballots.size()-1).size();i++){
            String candidate=ballots.get(ballots.size()-1).get(i);
            lastBallotMap.put(candidate, i+1);
        }
        List<String>ans=new ArrayList<>(points.keySet());
        for(String s:ans)System.out.println(points.get(s));
        secondStrategy(ans);
        return ans;
    }
    public List<String> secondStrategy(List<String>ans){
        ans.sort((a,b)->{
            if(points.get(a)!=points.get(b)){
                return points.get(b)-points.get(a);
            }
            return compare(a, b);
        });        
        return ans;   
    }
    public List<String> firstStrategy(List<String>ans){
        ans.sort((a,b)->{
            if(points.get(a)!=points.get(b)){
                return points.get(b)-points.get(a);
            }
            return lastBallotMap.get(a)-lastBallotMap.get(b);
        });        
        return ans;   
    }

    public int compare(String a, String b) {
        int[] aVotes = indexVotesMap.get(a);
        int[] bVotes = indexVotesMap.get(b);
        for (int i = 0; i < 3; i++) {
            int cmp = Integer.compare(bVotes[i], aVotes[i]);
            if (cmp != 0) return cmp;
        }
        return a.compareTo(b); // fallback if exactly same        
    }

}
