package rateLimiterType;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public interface SlidingWindowRequestCounter {
    public void recordRequest(Request request,GroupBy groupBy);
    public int getRequestCount(String attribute);
}
