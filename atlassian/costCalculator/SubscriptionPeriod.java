import java.time.LocalDate;

public class SubscriptionPeriod {
    private PricingPlan plan;
    private LocalDate from ;
    private LocalDate to;
    public SubscriptionPeriod(PricingPlan plan, LocalDate from, LocalDate to) {
        this.plan = plan;
        this.from = from;
        this.to = to;
    }
    public PricingPlan getPlan() {
        return plan;
    }
    public void setPlan(PricingPlan plan) {
        this.plan = plan;
    }
    public LocalDate getFrom() {
        return from;
    }
    public void setFrom(LocalDate from) {
        this.from = from;
    }
    public LocalDate getTo() {
        return to;
    }
    public void setTo(LocalDate to) {
        this.to = to;
    }
    
}
