package atlassian.atlassianRateLimiter;
public interface RateLimiter {
    public boolean allowRequest(String userId);
}
