package com.example.ratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ratelimiter.FixedWindow.CreditsFixedWindow;
import com.example.ratelimiter.FixedWindow.CreditsRateLimiterImpl;
import com.example.ratelimiter.FixedWindow.CreditsRateLimiterThreadSafeImpl;
import com.example.ratelimiter.FixedWindow.FixedWindow;
import com.example.ratelimiter.SlidingWindow.NormalRateLimiterImpl;
import com.example.ratelimiter.SlidingWindow.SlidingWindowRateLimiterImpl;

@SpringBootApplication
public class RatelimiterApplication {

	public static void main(String[] args) {

		RateLimiter rateLimiter=new SlidingWindowRateLimiterImpl(2000L,3,3);
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        try{
			Thread.sleep(2000);
		}catch(Exception e){

		}
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));

        // System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        // System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
	}

}
