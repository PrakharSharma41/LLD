package com.interview.demo;

import java.time.LocalDate;

public class SubscriptionPeriod {
    String productName;
    PricingPlan plan;
    LocalDate form;
    SubscriptionPeriod(String productName,PricingPlan plan,LocalDate from){
        this.productName=productName;
        this.plan=plan;
        this.form=from;
    }
    public String getProductName() {
        return productName;
    }
    public PricingPlan getPlan() {
        return plan;
    }
    public LocalDate getForm() {
        return form;
    }
    
}
