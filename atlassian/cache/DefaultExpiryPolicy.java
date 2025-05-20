public class DefaultExpiryPolicy implements ExpiryPolicy {
    @Override
    public boolean isExpired(long creationTime, long ttlInMillis) {
        return System.currentTimeMillis() - creationTime > ttlInMillis;
    }
}

// Time-Based Expiration Policies
// Sliding TTL (Access-Based Expiry)
// Fixed TTL (Time-To-Live)