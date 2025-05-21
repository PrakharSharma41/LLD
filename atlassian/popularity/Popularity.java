import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Popularity {
    Map<Integer,Integer>map;
    TreeMap<Integer,Set<Integer>>countToContent;
    Popularity(){
        map=new HashMap<>();
        countToContent=new TreeMap<>();
    }
    public void increasePopularity(int content){
        int popularity=map.getOrDefault(content, 0);
        if(map.containsKey(content)==false){
            countToContent.putIfAbsent(popularity+1, new HashSet<>());
            countToContent.get(popularity+1).add(content);
        }else{
            removeFromCountToContent(content,popularity);
            countToContent.putIfAbsent(popularity+1, new HashSet<>());
            countToContent.get(popularity+1).add(content);
        }
        map.put(content, popularity+1);
    }
    public void removeFromCountToContent(int content,int popularity){
        Set<Integer>set=countToContent.get(popularity);
        set.remove(content);
        if(set.size()==0)countToContent.remove(popularity);
    }
    public void decreasePopularity(int content){
        int popularity=map.getOrDefault(content,0);
        if(popularity==0)return;
        removeFromCountToContent(content,popularity);
        popularity--;
        if(popularity>0){
            countToContent.putIfAbsent(popularity, new HashSet<>());
            countToContent.get(popularity).add(content);
            map.put(content, popularity);
        }else{
            map.remove(content);
        }
    }
    public int getMaxPopular(){
        if(countToContent.size()==0)return -1;
        Set<Integer>set=countToContent.lastEntry().getValue();
        Iterator<Integer>itr=set.iterator();
        if(itr.hasNext())return itr.next();
        return -1;
    }

}
