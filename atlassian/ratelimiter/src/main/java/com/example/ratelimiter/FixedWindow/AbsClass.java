package com.example.ratelimiter.FixedWindow;

import com.example.ratelimiter.RateLimiter;

public abstract class AbsClass implements RateLimiter{
    long windowSize;
    int maxRequests;
}
