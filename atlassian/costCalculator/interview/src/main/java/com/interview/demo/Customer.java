package com.interview.demo;

public class Customer {
    private String name;
    private SubscriptionPeriod customerSubscriptionPeriod;
    public Customer(String name) {
        this.name = name;
    }
    public SubscriptionPeriod getCustomerSubscriptionPeriod() {
        return customerSubscriptionPeriod;
    }
    public void setCustomerSubscriptionPeriod(SubscriptionPeriod customerSubscriptionPeriod) {
        this.customerSubscriptionPeriod = customerSubscriptionPeriod;
    }
    public String getName() {
        return name;
    }
    
}
