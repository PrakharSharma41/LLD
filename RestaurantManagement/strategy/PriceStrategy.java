package RestaurantManagement.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import RestaurantManagement.model.Restaurant;
import RestaurantManagement.model.User;
import RestaurantManagement.utils.FoodKartUtil;

public class PriceStrategy implements RestaurantDisplayStrategy{
    public List<Restaurant> findRestaurants(List<Restaurant> listOfRestaurants, User currentUser) {
        List<Restaurant> list = FoodKartUtil.getMatchingRestaurant(listOfRestaurants, currentUser);
        Collections.sort(list, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant restaurant1, Restaurant restaurant2) {
                return Double.compare(restaurant2.getFoodItem().getPrice(), restaurant1.getFoodItem().getPrice());
            }
        });

        return list;
    }
}
