import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    int[] unique;
    HashMap<Integer,Integer>mp=new HashMap<>();
    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }    
    public int partition(int low,int high){
        int pivot=mp.get(unique[high]),pivotLoc=low;
        for(int i=low;i<high;i++){
            if(mp.get(unique[i])<pivot){
                swap(pivotLoc,i);
                pivotLoc++;
            }
        }
        swap(pivotLoc,high);
        return pivotLoc;
    }
    public void quickSelect(int low,int high,int k){
        if(low==high)return ;
        int pivot=partition(low,high);
        if(pivot==k)return;
        else if(pivot>k)quickSelect(low,pivot-1,k);
        else quickSelect(pivot+1,high,k);
    }
    public int[] topKFrequent(int[] nums, int k) {
        for(int i:nums){
            mp.put(i,mp.getOrDefault(i,0)+1);            
        }
        int n=mp.size(),i=0;
        unique=new int[n];
        for(int num:mp.keySet()){
            unique[i++]=num;
        }
        quickSelect(0,n-1,n-k);
        return Arrays.copyOfRange(unique, n - k, n);
    }
}