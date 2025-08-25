import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class OrderBook {
    String symbol;
    NavigableMap<Double,Deque<Order>>buyBook;
    NavigableMap<Double,Deque<Order>>sellBook;
    public OrderBook(String symbol){
        this.symbol=symbol;
        buyBook=Collections.synchronizedNavigableMap(new TreeMap<>(Comparator.reverseOrder()));
        sellBook=Collections.synchronizedNavigableMap(new TreeMap<>());
    }    
    public void addOrder(Order order,OrderMatchingStrategy strategy){
        if(order.getOrderType()==OrderType.BUY){
            buyBook.computeIfAbsent(order.getPrice(), k->new LinkedList<>()).add(order);
        }else{
            sellBook.computeIfAbsent(order.getPrice(), k->new LinkedList<>()).add(order);
        }
        synchronized(buyBook){
            synchronized(sellBook){
                strategy.matchOrder(buyBook, sellBook);
            }
        }
    }

}
