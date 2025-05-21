package com.interview.demo;

import java.time.LocalDate;
import java.util.HashMap;

public class CustomerService {
    private HashMap<String,Customer>nameToCustomer;

    public CustomerService() {
        nameToCustomer=new HashMap<>();
    }
    public Customer createCustomer(String customerName){
        if(nameToCustomer.containsKey(customerName)){
            return nameToCustomer.get(customerName);
        }
        Customer customer=new Customer(customerName);
        nameToCustomer.put(customerName, customer);
        return  customer;
    }
    public void setCustomerSubscription(String customerName,String productName,PricingPlan plan,LocalDate start ){
        Customer customer=createCustomer(customerName);
        SubscriptionPeriod subscriptionPeriod=new SubscriptionPeriod(plan, productName, start);
        customer.setCustomerSubscriptionPeriod(subscriptionPeriod);
    }
    public SubscriptionPeriod getSubscriptionPeriod(String customerName){
        if(nameToCustomer.containsKey(customerName)==false){
            System.out.println("customer "+customerName+" does not exist");
            return null;
        }
        Customer customer=nameToCustomer.get(customerName);
        return customer.getCustomerSubscriptionPeriod();
    }
    public Customer getCustomer(){
        for(String name: nameToCustomer.keySet()){
            return nameToCustomer.get(name);
        }
        return null;
    }
    
}
