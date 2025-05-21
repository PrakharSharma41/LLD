package com.interview.demo;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CostExplorer {
    private CustomerService customerService;
    Map<LocalDate,List<Double>> monthly;
    CostExplorer(){
        customerService=new CustomerService();
    }
    public void createSubscription(String customerName,String productName,PricingPlan plan,LocalDate start){
        customerService.setCustomerSubscription(customerName, productName, plan, start);
    }
    public void getMonthlyCosts(){
        Customer customer=customerService.getCustomer();
        SubscriptionPeriod period=customer.getCustomerSubscriptionPeriod();
        double monthPrice=period.getPlan().getPrice();

        LocalDate date=period.getFrom();



        int monthValue=date.getMonthValue();

        List<Double> monthlyPrice=new ArrayList<>();
        for(int i=0;i<=12;i++)monthlyPrice.add(0.0);
    
        // 2022-03-10
        int dayOfStartMonth=date.getDayOfMonth();

        // 9.99- (10 days price)
        double startMonthPrice=monthPrice-((double)((dayOfStartMonth-1)*monthPrice)/30.0);
        monthlyPrice.set(monthValue, startMonthPrice);


        for(int i=monthValue+1;i<=12;i++){
            monthlyPrice.set(i, monthPrice);
        }
        for(int i=1;i<=12;i++){
            System.out.print(monthlyPrice.get(i)+" ");
        }
    }
    public void getYearlyCosts(){
        Customer customer=customerService.getCustomer();
        SubscriptionPeriod period=customer.getCustomerSubscriptionPeriod();
        double monthlyPrice=period.getPlan().getPrice();
        LocalDate date=period.getFrom();
        int monthValue=date.getMonthValue();
        double totalCost=(12-monthValue+1)*monthlyPrice;
        System.out.println(totalCost);
    }

}
