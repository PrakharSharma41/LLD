package com.interview.demo;

public enum PricingPlan {
    BASIC(30.00),
    STANDARD(49.99), 
    PREMIUM(249.99);
    double price;
    PricingPlan(double price){
        this.price=price;
    }    
    public double getPrice(){
        return price;
    }
}
