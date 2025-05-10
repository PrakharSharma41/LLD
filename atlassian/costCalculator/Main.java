import java.time.LocalDate;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CostExplorer costExplorer=new CostExplorer();
        costExplorer.createSubscription("c1", LocalDate.of(2025,1,1), LocalDate.of(2025, 3, 31), PricingPlan.BASIC); 
          
        costExplorer.findCost("c1", CostType.MONTHLY);
    }
}
