package RestaurantManagement.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import RestaurantManagement.model.Restaurant;
import RestaurantManagement.model.User;
import iteratorDesign.Iterator;

public class FoodKartUtil {
    public static List<Restaurant> getMatchingRestaurant(List<Restaurant> listOfRestaurants, User currentUser) {
        List<Restaurant> list = new ArrayList<>(listOfRestaurants);
        return list.stream().filter(restaurant->{
            return restaurant.isLocationServiceAble(currentUser.getPincode()) && restaurant.isEnoughQuantityAvailable();
        }).collect(Collectors.toList());
    }
}