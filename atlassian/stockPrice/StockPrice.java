import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class StockPrice {
    TreeMap<Integer,Integer>freq;
    TreeMap<Integer,Integer>timeToPrice;
    int latest=-1;
    public StockPrice() {
        freq=new TreeMap<>();
        timeToPrice=new TreeMap<>();
    }
    
    public void update(int time, int price) {        
        if(timeToPrice.containsKey(time)){
            int p=timeToPrice.get(time);
            freq.put(p,freq.get(p)-1);
            if(freq.get(p)==0)freq.remove(p);
        }
        latest=Math.max(latest,time);        
        timeToPrice.put(time,price);
        freq.put(price,freq.getOrDefault(price,0)+1);
    }
    
    public int current() {
        return timeToPrice.get(latest);
    }
    
    public int maximum() {
        return freq.lastKey();
    }
    
    public int minimum() {
        return freq.firstKey();
    }

    public Integer getMaxPriceTill(int time) {
        Integer max = null;
        for (Map.Entry<Integer, Integer> entry : timeToPrice.headMap(time + 1).entrySet()) {
            if (max == null || entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }    
    public Integer getMaxPriceTillAfterAllUpdates(int t) {
        // Map.Entry<Integer, Integer> entry = timeToMaxPricePrefix.floorEntry(time);
        // return entry != null ? entry.getValue() : null;

        List<int[]>sortedTimePrice=new ArrayList<>();
        int[] maxPrice=new int[1];
        maxPrice[0]=-1;
        timeToPrice.forEach((time,price)->{
            maxPrice[0]=Math.max(maxPrice[0],price);
            sortedTimePrice.add(new int[]{time,maxPrice[0]});
        });
        int start=0,end=sortedTimePrice.size()-1,mid;maxPrice[0]=-1;
        while(start<=end){
            mid=(start+end)/2;
            if(sortedTimePrice.get(mid)[0]<=t){
                maxPrice[0]=Math.max(maxPrice[0],sortedTimePrice.get(mid)[1]);
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return maxPrice[0];
    }    
}
