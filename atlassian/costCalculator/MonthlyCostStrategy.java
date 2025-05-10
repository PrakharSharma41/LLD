import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MonthlyCostStrategy implements CostCalculatorStrategy{

    @Override
    public void findCost(String customerName,List<SubscriptionPeriod> customerSubscription) {
        int year = LocalDate.now().getYear(); // Or make it configurable
        Map<YearMonth, Double> monthlyBill = new TreeMap<>();
        // Initialize all months of the year with 0
        for (int month = 1; month <= 12; month++) {
            monthlyBill.put(YearMonth.of(year, month), 0.0);
        }
        for(SubscriptionPeriod period: customerSubscription){
            LocalDate from=period.getFrom();
            LocalDate to=period.getTo();
            PricingPlan plan=period.getPlan();
            LocalDate periodStart = from.isBefore(LocalDate.of(year, 1, 1)) ? LocalDate.of(year, 1, 1) : from;
            LocalDate periodEnd = to.isAfter(LocalDate.of(year, 12, 31)) ? LocalDate.of(year, 12, 31) : to;                        
            YearMonth ymStart = YearMonth.from(periodStart);
            YearMonth ymEnd = YearMonth.from(periodEnd);

            while (!ymStart.isAfter(ymEnd)) {
                monthlyBill.put(ymStart, monthlyBill.getOrDefault(ymStart, 0.0) + plan.getPrice());
                ymStart = ymStart.plusMonths(1);
            }            
        }
        System.out.println("Monthly bills for customer: " + customerName);
        for (Map.Entry<YearMonth, Double> entry : monthlyBill.entrySet()) {
            System.out.printf("%s -> $%.2f\n", entry.getKey(), entry.getValue());
        }        

    }    


    // @Override
    // public void findCost(String customerName,List<SubscriptionPeriod> customerSubscription) {
    //     double totalCost = 0.0;

    //     for (SubscriptionPeriod sub : customerSubscription) {
    //         long months = ChronoUnit.MONTHS.between(
    //             sub.getFrom().withDayOfMonth(1),
    //             sub.getTo().withDayOfMonth(1).plusMonths(1)
    //         );

    //         totalCost += months * sub.getPlan().getPrice();
    //     }

    //     System.out.printf("Total monthly cost for %s: $%.2f\n", customerName, totalCost);
    //     // not sure what to do here since question statement that i have is vague
    // }    

}
