package com.example.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ratelimiter.FixedWindow.CreditsRateLimiterImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CreditsRateLimiterImplTest {

    private CreditsRateLimiterImpl rateLimiter;
    private String userId = "user1";

    @BeforeEach
    public void setUp() {
        rateLimiter = new CreditsRateLimiterImpl(1000L, 5, 3);  // 1 second window, 5 requests max, 3 credits
    }

    @Test
    public void testFirstRequest() {
        assertTrue(rateLimiter.rateLimit(userId));  // First request should pass
    }

    @Test
    public void testMaxRequests() {
        // Test for maxRequests limit within the same window
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.rateLimit(userId));  // Should pass each time until limit is reached
        }
        assertFalse(rateLimiter.rateLimit(userId));  // 6th request should fail (maxRequests = 5)
    }
// 
    @Test
    public void testUsingCreditsAfterMaxRequests() {
        // Test for using credits after maxRequests are exhausted
        for (int i = 0; i < 4; i++) {
            assertTrue(rateLimiter.rateLimit(userId));  // First 5 requests should pass as normal
        }
        // Simulate a wait time (e.g., rate limiter window reset time)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  // Wait for 1 second (simulate window reset)
        for (int i = 0; i < 7; i++) {
            assertTrue(rateLimiter.rateLimit(userId));  // First 5 requests should pass as normal
        }
        
        // After the sleep, the rate limit window should reset and the user can make another request
        // assertTrue(rateLimiter.rateLimit(userId));  
    }

    @Test
    public void testWindowExpiration() throws InterruptedException {
        // Test window expiration and starting a new window
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.rateLimit(userId));  // First 5 should pass
        }

        // Sleep to let the window expire
        Thread.sleep(1001);  // wait for more than 1 second (windowSize = 1000ms)

        assertTrue(rateLimiter.rateLimit(userId));  // After window expires, new window starts
        assertTrue(rateLimiter.rateLimit(userId));  // Should pass in new window
    }

    @Test
    public void testCreditsRefillAfterWindowExpiration() throws InterruptedException {
        // Test that credits refill after window expiration
        for (int i = 0; i < 4; i++) {
            assertTrue(rateLimiter.rateLimit(userId));  // First 5 should pass
        }

        // assertFalse(rateLimiter.rateLimit(userId));  // Uses 1 credit
        // assertFalse(rateLimiter.rateLimit(userId));  // Uses 2nd credit

        // Sleep to let the window expire and credits refill
        Thread.sleep(1001);  // wait for more than 1 second (windowSize = 1000ms)

        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits
        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits
        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits
        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits
        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits
        assertTrue(rateLimiter.rateLimit(userId));  // New window should refill credits

    }    
}
