package RestaurantManagement.service;

import java.util.List;

import RestaurantManagement.model.Restaurant;

public interface RestaurantService{
    void registerRestaurant(String restaurantName,List<String>pincodes,String foodname,Integer foodQuantity,Double foodPrice);
    void updateQuantity(String restaurantName,Integer quantityToAdd);
    void rateRestaurant(String restaurantName,Integer rating,String comment);
    List<Restaurant>showRestaurant(String criteria);
}
