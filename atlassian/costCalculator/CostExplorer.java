import java.time.LocalDate;

public class CostExplorer {
    CostCalculatorStrategy costStrategy;
    CustomerService customerService;
    CostExplorer(){
        this.customerService=new CustomerService();
    }
    public void createSubscription(String customerName,LocalDate start,LocalDate end,PricingPlan plan){
        customerService.setCustomerPlan(customerName, start, end, plan);
    }
    public void findCost(String customerName,CostType costType){
        costStrategy=CostFactory.findCalculatorType(costType);
        costStrategy.findCost(customerName,customerService.getCustomerSubscription(customerName));        
    }
}
