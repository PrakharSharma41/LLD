package com.interview.demo;

public class Customer {
    SubscriptionPeriod period;
    String customerName;
    public Customer(String customerName) {
        this.customerName = customerName;
    }
    public SubscriptionPeriod getPeriod() {
        return period;
    }
    public void setPeriod(SubscriptionPeriod period) {
        this.period = period;
    }
    public String getCustomerName() {
        return customerName;
    }    
}
