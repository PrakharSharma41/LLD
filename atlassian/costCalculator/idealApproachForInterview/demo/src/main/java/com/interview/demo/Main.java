package com.interview.demo;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CostExplorer costExplorer=new CostExplorer();
        costExplorer.createSubscriptionPlan("c1", "jira", PricingPlan.BASIC, LocalDate.of(2025, 3, 10));
        costExplorer.getMonthlyCost();
        costExplorer.getYearlyCost();
    }
}
