public interface ExpiryPolicy {
    boolean isExpired(long creationTime, long ttlInMillis);
}