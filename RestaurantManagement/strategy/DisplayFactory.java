package RestaurantManagement.strategy;

public class DisplayFactory {
    public static RestaurantDisplayStrategy getStrategy(String criteria){
        if(criteria.equals("price")){
            return new PriceStrategy();
        }
        else{
            return new RatingStrategy();
        }
    } 
}
