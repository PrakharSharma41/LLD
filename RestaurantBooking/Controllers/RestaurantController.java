package RestaurantBooking.Controllers;

import java.util.List;

import RestaurantBooking.RestaurantSearchStrategy;
import RestaurantBooking.model.Restaurant;
import RestaurantBooking.model.User;
import RestaurantBooking.service.RestaurantService;

public class RestaurantController {
    RestaurantService restaurantService;
    
    public RestaurantController() {
        restaurantService=new RestaurantService(1);
    }

    public Restaurant createRestaurant(User user,String name,String city,int numberOfTables){
        return restaurantService.createRestaurant(user, name, city, numberOfTables);
    }
    public void showAvailableSchedules(String restaurantName){
        System.out.println("askjdasj");
        restaurantService.showAvailableSchedules(restaurantName);
    }
    public void searchRestaurant(RestaurantSearchStrategy strategy){
        List<Restaurant>restaurants=getAllRestaurants();
        restaurants=strategy.searchRestaurant(restaurants);
        for(Restaurant r:restaurants){
            System.out.println(r);
        }
    }
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
}
