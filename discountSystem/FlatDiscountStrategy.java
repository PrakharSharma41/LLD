class FlatDiscountStrategy implements DiscountStrategy {
    private double amount;

    public FlatDiscountStrategy(double amt) {
        this.amount = amt;
    }

    @Override
    public double calculate(double baseAmount) {
        return Math.min(amount, baseAmount);
    }
}