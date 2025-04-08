
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter{
    private int refillRate;
    private int capacity;
    private ConcurrentHashMap<String,TokenBucket>userBuckets=new ConcurrentHashMap<>();
    public TokenBucketRateLimiter(int refillRate, int capacity) {
        this.refillRate = refillRate;
        this.capacity = capacity;
    }
    public boolean allowRequest(String userId){
        TokenBucket bucket = userBuckets.computeIfAbsent(userId,
                id -> new TokenBucket(capacity, System.nanoTime()));        
        synchronized(bucket){
            refillTokens(bucket);
            if(bucket.getTokens()>1){
                bucket.setTokens(bucket.getTokens()-1);
                return true;
            }
        }
        return false;
    }
    public void refillTokens(TokenBucket bucket){
        long now=System.currentTimeMillis();
        bucket.setTokens((int)Math.min(capacity,bucket.getTokens()+(now-bucket.getLastRefillTime())*refillRate));
        bucket.setLastRefillTime(now);
    }
}
