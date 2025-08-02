class PercentageWithCapStrategy implements DiscountStrategy {
    private double percent;
    private double cap;

    public PercentageWithCapStrategy(double pct, double capVal) {
        this.percent = pct;
        this.cap     = capVal;
    }

    @Override
    public double calculate(double baseAmount) {
        double disc = (percent / 100.0) * baseAmount;
        return disc > cap ? cap : disc;
    }
}