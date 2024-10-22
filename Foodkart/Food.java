package Foodkart;

public class Food {
     String foodName;
    private Integer foodPrice;
    private Integer initialQuantity=0;
    public Food(String foodName, Integer foodPrice, Integer initialQuantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.initialQuantity = initialQuantity;
    }
    public String getFoodName() {
        return foodName;
    }
    @Override
    public String toString() {
        return "Food [foodName=" + foodName + ", foodPrice=" + foodPrice + ", initialQuantity=" + initialQuantity + "]";
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Integer getFoodPrice() {
        return foodPrice;
    }
    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }
    public Integer getInitialQuantity() {
        return initialQuantity;
    }
    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity+= initialQuantity;
    }

}
