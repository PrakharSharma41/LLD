class PercentageDiscountStrategy implements DiscountStrategy {
    private double percent;

    public PercentageDiscountStrategy(double pct) {
        this.percent = pct;
    }

    @Override
    public double calculate(double baseAmount) {
        return (percent / 100.0) * baseAmount;
    }
}