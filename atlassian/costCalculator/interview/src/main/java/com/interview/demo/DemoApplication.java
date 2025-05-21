package com.interview.demo;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		CostExplorer costExplorer=new CostExplorer();
		costExplorer.createSubscription("C1", "Jira", PricingPlan.BASIC, LocalDate.of(2022, 03, 10));
		costExplorer.getYearlyCosts();
		costExplorer.getMonthlyCosts();
		costExplorer.getMonthlyCosts();
	}

}
