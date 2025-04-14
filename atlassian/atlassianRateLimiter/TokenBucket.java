package atlassian.atlassianRateLimiter;

public class TokenBucket {
    int tokens;
    long lastRefillTime;
    public TokenBucket(int tokens, long lastRefillTime) {
        this.tokens = tokens;
        this.lastRefillTime = lastRefillTime;
    }
    public int getTokens() {
        return tokens;
    }
    public long getLastRefillTime() {
        return lastRefillTime;
    }
    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
    public void setLastRefillTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }
    
}
