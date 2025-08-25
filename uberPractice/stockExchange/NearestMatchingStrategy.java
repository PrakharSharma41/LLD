import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NearestMatchingStrategy implements OrderMatchingStrategy{

    @Override
    public void matchOrder(NavigableMap<Double, Deque<Order>> buyBook, NavigableMap<Double, Deque<Order>> sellBook) {
        while(!buyBook.isEmpty()&&!sellBook.isEmpty()){
            Deque<Order>buyOrderList=buyBook.firstEntry().getValue();
            Deque<Order>sellOrderList=sellBook.firstEntry().getValue();
            boolean noMoreMatch=false;
            while(!buyOrderList.isEmpty()&&!sellOrderList.isEmpty()){
                Order buyOrder=buyOrderList.getFirst();
                Order sellOrder=sellOrderList.getFirst();
                if(buyOrder.getPrice()<sellOrder.getPrice()){
                    noMoreMatch=true;break;
                }
                int minQuantity=Math.min(buyOrder.getQuantity(),sellOrder.getQuantity());
                buyOrder.reduceQuantity(minQuantity);
                sellOrder.reduceQuantity(minQuantity);
                System.out.println(buyOrder+" executed");
                System.out.println(sellOrder+" executed");

                if(buyOrder.getQuantity()==0)buyOrderList.removeFirst();
                if(sellOrder.getQuantity()==0)sellOrderList.removeFirst();
            }
            if(buyOrderList.size()==0)buyBook.remove(buyBook.firstKey());
            if(sellOrderList.size()==0)sellBook.remove(sellBook.firstKey());
            if(noMoreMatch)break;
        }
    }
    
}
