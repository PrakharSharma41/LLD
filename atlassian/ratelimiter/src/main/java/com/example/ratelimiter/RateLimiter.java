package com.example.ratelimiter;

public interface RateLimiter {
    boolean rateLimit(String userId);
}
