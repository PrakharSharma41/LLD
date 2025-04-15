package com.example.ratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ratelimiter.FixedWindow.CreditsFixedWindow;
import com.example.ratelimiter.FixedWindow.CreditsRateLimiterImpl;
import com.example.ratelimiter.FixedWindow.FixedWindow;
import com.example.ratelimiter.SlidingWindow.NormalRateLimiterImpl;

@SpringBootApplication
public class RatelimiterApplication {

	public static void main(String[] args) {

		RateLimiter rateLimiter=new CreditsRateLimiterImpl(1000L,5,3);
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        try{
			Thread.sleep(1000);
		}catch(Exception e){

		}
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
        System.out.println("Sent to API Gateway Server? " + (rateLimiter.rateLimit("1234") ? "Yes" : "No"));
	}

}
