import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public interface OrderMatchingStrategy {
    void matchOrder(NavigableMap<Double, Deque<Order>> buyBook, NavigableMap<Double, Deque<Order>> sellBook);
}
