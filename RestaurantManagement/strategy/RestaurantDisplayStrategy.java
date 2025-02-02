package RestaurantManagement.strategy;

import java.util.List;

import RestaurantManagement.model.Restaurant;
import RestaurantManagement.model.User;

public interface RestaurantDisplayStrategy {
    public List<Restaurant> findRestaurants(List<Restaurant> listOfRestaurants, User currentUser);
}
