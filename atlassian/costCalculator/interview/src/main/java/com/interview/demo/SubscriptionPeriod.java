package com.interview.demo;

import java.time.LocalDate;

public class SubscriptionPeriod {
    private PricingPlan plan;
    private LocalDate from;
    private String productName;
    public SubscriptionPeriod(PricingPlan plan,String productName, LocalDate from) {
        this.plan = plan;
        this.from = from;
        this.productName=productName;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public PricingPlan getPlan() {
        return plan;
    }
    public void setPlan(PricingPlan plan) {
        this.plan = plan;
    }
    public LocalDate getFrom() {
        return from;
    }
    public void setFrom(LocalDate from) {
        this.from = from;
    }
    
}
