package rateLimiterType;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRequestCounterImpl implements SlidingWindowRequestCounter{
    private final ConcurrentHashMap<String,Deque<Request>>countMap;
    private final long windowSizeMillis;
    private final AttributeExtractorStrategy strategy;
    public SlidingWindowRequestCounterImpl( long windowSizeMillis,AttributeExtractorStrategy strategy) {
        this.countMap = new ConcurrentHashMap<>();
        this.windowSizeMillis = windowSizeMillis;
        this.strategy=strategy;
    }
    @Override
    public void recordRequest(Request request,GroupBy groupBy) {
        // long now=request.getTimeStamp();
        String key=strategy.extract(request, groupBy);
        countMap.compute(key, (k,deque)->{
            if(deque==null){
                deque = new ConcurrentLinkedDeque<>();
            }
            deque.addLast(request);
            return deque;//If the function returns null, the key is removed from the map.
        });
    }

    @Override
    public int getRequestCount(String attribute) {
        long now=System.currentTimeMillis();
        Deque<Request> deque = countMap.get(attribute);
        if (deque == null) return 0;
        synchronized(deque){
            while(!deque.isEmpty()&&deque.peekFirst().getTimeStamp()<now-windowSizeMillis)deque.pollFirst();
        }
        return deque.size();
    }

}
