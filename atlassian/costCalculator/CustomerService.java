import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
    private HashMap<String,Customer>nameToCustomer;
    CustomerService(){
        nameToCustomer=new HashMap<>();
    }
    public Customer createCustomer(String name){
        if(nameToCustomer.get(name)!=null)return nameToCustomer.get(name);
        Customer customer=new Customer(name);
        nameToCustomer.put(name, customer);
        return customer;
    }
    public void setCustomerPlan(String name, LocalDate startDate,LocalDate endDate,PricingPlan plan){
        Customer customer=createCustomer(name);
        SubscriptionPeriod customerSubscribtion=new SubscriptionPeriod(plan, startDate, endDate);
        customer.setCustomerSubscription(customerSubscribtion);        
    }
    public List<SubscriptionPeriod> getCustomerSubscription(String name){
        if(nameToCustomer.get(name)==null)return null;
        return nameToCustomer.get(name).getCustomerSubscription();
    }
}
