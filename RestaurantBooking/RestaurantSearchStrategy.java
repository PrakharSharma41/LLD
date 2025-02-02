package RestaurantBooking;

import java.util.List;

import RestaurantBooking.model.Restaurant;

public interface RestaurantSearchStrategy {
    public List<Restaurant> searchRestaurant(List<Restaurant>restaurants);
}
