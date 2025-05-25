package com.interview.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CostExplorer {
    Customer customer;
    public void createSubscriptionPlan(String customerName,String prouctName,PricingPlan plan,LocalDate start){
        customer=new Customer(customerName);
        SubscriptionPeriod period=new SubscriptionPeriod(prouctName, plan, start);
        customer.setPeriod(period);
    }
    public void getMonthlyCost(){
        if(customer==null){
            return ;
        }
        SubscriptionPeriod period=customer.getPeriod();
        LocalDate planStartDate=period.getForm();
        int monthValue=planStartDate.getMonthValue();
        double monthlyPrice=period.getPlan().getPrice();
        List<Double>costs=new ArrayList<>();
        for(int i=0;i<=12;i++)costs.add(0.0);

        double firstMonthPrice=monthlyPrice-(monthlyPrice*(planStartDate.getDayOfMonth()-1))/30;
        costs.set(monthValue, firstMonthPrice);
        for(int i=monthValue+1;i<=12;i++){
            costs.set(i, monthlyPrice);
        }
        for(int i=1;i<=12;i++){
            System.out.print(costs.get(i)+" ");
        }        
        System.out.println();
    }
    public void getYearlyCost(){
        if(customer==null){
            return ;
        }
        SubscriptionPeriod period=customer.getPeriod();
        LocalDate planStartDate=period.getForm();
        int monthValue=planStartDate.getMonthValue();
        double monthlyPrice=period.getPlan().getPrice();
        double firstMonthPrice=monthlyPrice-(monthlyPrice*(planStartDate.getDayOfMonth()-1))/30;
        double yearlyCost=(12-monthValue)*monthlyPrice+firstMonthPrice;
        System.out.println("yearly cost is "+yearlyCost);
    }
}
