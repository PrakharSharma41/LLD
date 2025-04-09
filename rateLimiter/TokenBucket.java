import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {

    class TokenBucket{
        long refillTime;
        int tokenCount;
        public TokenBucket(double tokens, long lastRefillTimestamp) {
            this.tokens = tokens;
            this.lastRefillTimestamp = lastRefillTimestamp;
        }        
    }
    private int capacity;
    private int refillRate;
    ConcurrentHashMap<String,TokenBucket>users=new ConcurrentHashMap<>();
    TokenBucketRateLimiter(int capacity,int refillRate){
        this.capacity=capacity;this.refillRate=refillRate;
    }
}
