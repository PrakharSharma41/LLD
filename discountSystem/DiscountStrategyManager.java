
class DiscountStrategyManager {
    private static DiscountStrategyManager instance;

    private DiscountStrategyManager() {}

    public static synchronized DiscountStrategyManager getInstance() {
        if (instance == null) {
            instance = new DiscountStrategyManager();
        }
        return instance;
    }

    public DiscountStrategy getStrategy(StrategyType type, double param1, double param2) {
        switch(type) {
            case FLAT:
                return new FlatDiscountStrategy(param1);
            case PERCENT:
                return new PercentageDiscountStrategy(param1);
            case PERCENT_WITH_CAP:
                return new PercentageWithCapStrategy(param1, param2);
            default:
                return null;
        }
    }
}