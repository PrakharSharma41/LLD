import java.util.List;

public interface CostCalculatorStrategy {
    public void findCost(String customerNam,List<SubscriptionPeriod> customerSubscription);
}
