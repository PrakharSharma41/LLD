class SeasonalOffer extends Discount {
    private double percent;
    private String category;
    private DiscountStrategy strat;

    public SeasonalOffer(double pct, String cat) {
        this.percent  = pct;
        this.category = cat;
        this.strat    = DiscountStrategyManager.getInstance()
                            .getStrategy(StrategyType.PERCENT, percent, 0.0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getDiscount(Cart cart) {
        double subtotal = 0.0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getCategory().equals(category)) {
                subtotal += item.itemTotal();
            }
        }
        return strat.calculate(subtotal);
    }

    @Override
    public String name() {
        return "Seasonal Offer " + (int)percent + "% off " + category;
    }
}