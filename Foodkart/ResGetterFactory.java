package Foodkart;

public class ResGetterFactory {
    public static RestaurantGetter get(ResGetterType type){
        if(type==ResGetterType.PRICE)return new ResGetterByPrice();
        else return new ResGetterByRating();
    }
}
