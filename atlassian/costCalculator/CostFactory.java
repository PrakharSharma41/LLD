public class CostFactory {
    public static CostCalculatorStrategy findCalculatorType(CostType costType){
        if(costType==CostType.YEARLY)return new YearlyCostStrategy();
        return new MonthlyCostStrategy();
    }
}