import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Exchange {
    Map<String,OrderBook>books;
    OrderMatchingStrategy strategy;
    Exchange(OrderMatchingStrategy strategy){
        this.strategy=strategy;
        books=new ConcurrentHashMap<>();
    }
    void placeOrder(Order order){
        books.computeIfAbsent(order.getSymbol(), k->new OrderBook(order.getSymbol())).addOrder(order, strategy);
    }
}
