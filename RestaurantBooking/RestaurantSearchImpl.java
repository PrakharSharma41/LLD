package RestaurantBooking;

import java.util.List;

import RestaurantBooking.model.Restaurant;

public class RestaurantSearchImpl implements RestaurantSearchStrategy{

    @Override
    public List<Restaurant> searchRestaurant(List<Restaurant> restaurants) {
        return restaurants.subList(0, 2);
    }    
}
