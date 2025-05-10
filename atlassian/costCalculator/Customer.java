import java.util.ArrayList;
import java.util.List;

public class Customer {
    String name;
    List<SubscriptionPeriod> customerSubscription;
    Customer(String name){
        this.name=name;
        customerSubscription=new ArrayList<>();
    }
    public List<SubscriptionPeriod> getCustomerSubscription() {
        return customerSubscription;
    }
    public void setCustomerSubscription(SubscriptionPeriod subscription) {
        customerSubscription.add(subscription);
    }
    
}
