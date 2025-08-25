import java.util.LinkedList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        OrderMatchingStrategy strategy=new NearestMatchingStrategy();
        Exchange exchange=new Exchange(strategy);
        exchange.placeOrder(new Order(1, "Apple", 100.90, System.currentTimeMillis(), 10, OrderType.BUY));
        exchange.placeOrder(new Order(2, "Apple", 90.90, System.currentTimeMillis(), 1, OrderType.SELL));
        exchange.placeOrder(new Order(3, "Apple", 99.90, System.currentTimeMillis(), 3, OrderType.SELL));
        exchange.placeOrder(new Order(4, "Apple", 110.90, System.currentTimeMillis(), 9, OrderType.SELL));
// buybook:
// 100 0, 20, 5
// 199

// sellBook:
// 199 5, 24, 4
// 300       
    }
}