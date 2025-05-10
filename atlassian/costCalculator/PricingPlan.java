public enum PricingPlan{
    BASIC(9.99),
    STANDARD(49.99),
    PREMIUM(249.99);
    double price;
    PricingPlan(double price){
        this.price=price;
    }
    public double getPrice(){
        return this.price;
    }
}

enum CostType{
    YEARLY,
    MONTHLY
}