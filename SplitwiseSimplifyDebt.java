import java.util.*;

public class SplitwiseSimplifyDebt {
    public int minTransfers(int[][] transactions){
        Map<Integer,Integer>membervsBalance=new HashMap<>();
        for(int[] trn:transactions){
            int from=trn[0],to=trn[1],amount=trn[2];
            membervsBalance.put(from, membervsBalance.getOrDefault(from, 0)-amount);
            membervsBalance.put(to, membervsBalance.getOrDefault(to, 0)+amount);
        }
        List<Integer>balanceList=new ArrayList<>();
        for(int amount:membervsBalance.values()){
            if(amount!=0)balanceList.add(amount);
        }
        System.out.println("minimum transactions are "+dfs(balanceList,0));
    }
    public int dfs(List<Integer>balanceList,int currentIndex){
        
        if(balanceList.size()==0||currentIndex==balanceList.size())return 0;

        if(balanceList.get(currentIndex)==0){
            return dfs(balanceList,++currentIndex);
        }
        int minTransactions=Integer.MAX_VALUE;
        for(int i=currentIndex+1;i<balanceList.size();i++){
            if(balanceList.get(i)==0)continue;
            int nextVal=balanceList.get(i);
            if(balanceList.get(currentIndex)*balanceList.get(i)<0){
                balanceList.set(i, balanceList.get(i)+balanceList.get(currentIndex));
                minTransactions=Math.min(minTransactions,1+dfs(balanceList,currentIndex+1));
                balanceList.set(i, nextVal);
                if(nextVal+balanceList.get(currentIndex)==0)break;           
            }
        }
        return minTransactions;
    }
}

// I was asked this in Amazon interview in 2022. I solved it using min heap and max heap. Min heap will have all -ve transactions(essentially max heap with highest negative money) and max heap to all +ve transactions. 
//In each iteration, I pop top entries from both the heaps and then reduced the lower one to zero and other one will be again pushed in the heap after subtraction. I cleared that round.