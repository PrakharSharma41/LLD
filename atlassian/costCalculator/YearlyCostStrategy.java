import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class YearlyCostStrategy implements CostCalculatorStrategy{

    @Override
    public void findCost(String customerName, List<SubscriptionPeriod> customerSubscription) {
        double totalYearlyCost = 0.0;

        for (SubscriptionPeriod sub : customerSubscription) {
            double monthlyPrice = sub.getPlan().getPrice();
            totalYearlyCost += monthlyPrice * 12;
        }

        System.out.printf("Total yearly estimated cost for %s: $%.2f\n", customerName, totalYearlyCost);
    }
    // @Override
    // public void findCost(String customerName, List<SubscriptionPeriod> customerSubscription) {
    //     int year = LocalDate.now().getYear(); // Target unit year
    //     double totalCost = 0.0;

    //     for (SubscriptionPeriod sub : customerSubscription) {
    //         LocalDate from = sub.getFrom();
    //         LocalDate to = sub.getTo();
    //         PricingPlan plan = sub.getPlan();

    //         // Clamp period within target year
    //         LocalDate periodStart = from.isBefore(LocalDate.of(year, 1, 1)) ? LocalDate.of(year, 1, 1) : from;
    //         LocalDate periodEnd = to.isAfter(LocalDate.of(year, 12, 31)) ? LocalDate.of(year, 12, 31) : to;

    //         YearMonth ymStart = YearMonth.from(periodStart);
    //         YearMonth ymEnd = YearMonth.from(periodEnd);

    //         while (!ymStart.isAfter(ymEnd)) {
    //             totalCost += plan.getPrice();
    //             ymStart = ymStart.plusMonths(1);
    //         }
    //     }

    //     System.out.printf("Total estimated yearly cost for %s: $%.2f\n", customerName, totalCost);
    // }
    // @Override
    // public void findCost(String customerName,List<SubscriptionPeriod> customerSubscription) {
    //     // not sure what to do here since question statement that i have is vague
    //     double totalCost = 0.0;

    //     for (SubscriptionPeriod sub : customerSubscription) {
    //         long days = ChronoUnit.DAYS.between(sub.getFrom(), sub.getTo().plusDays(1));
    //         double years = days / 365.0;

    //         totalCost += years * sub.getPlan().getPrice() * 12; // 12 months in a year
    //     }

    //     System.out.printf("Total yearly cost for %s: $%.2f\n", customerName, totalCost);
    // }
    
}
