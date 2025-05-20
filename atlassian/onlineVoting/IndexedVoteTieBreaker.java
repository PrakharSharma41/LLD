import java.util.HashMap;
import java.util.Map;

public class IndexedVoteTieBreaker implements TieBreakStrategy{
    Map<String,int[]>indexVotesMap=new HashMap<>();

    IndexedVoteTieBreaker(Map<String,int[]>indexVotesMap){
        this.indexVotesMap=indexVotesMap;
    }
    @Override
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
