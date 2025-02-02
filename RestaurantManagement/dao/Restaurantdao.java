package RestaurantManagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RestaurantManagement.model.Restaurant;

public class Restaurantdao {
    Map<String,Restaurant>restaurants;
    public Restaurantdao() {
        restaurants=new HashMap<>();
    }
    public void registerRestaurant(Restaurant restaurant){
        if(!restaurants.containsKey(restaurant.getRestaurantName())){
            restaurants.put(restaurant.getRestaurantName(), restaurant);
            System.out.println("restaurant with name: "+restaurant.getRestaurantName()+" addedd successfully");
        }else{
            System.out.println("restaurant has already been added");
        }
    }
    public void updateQuantity(String restaurantName, Integer quantityToAdd) {
        Restaurant restaurant=restaurants.get(restaurantName);
        if(restaurant!=null){
            restaurant.getFoodItem().updateQuantity(quantityToAdd);
        }else{
            System.out.println("Restaurant does not exist");
        }
    }
    public Restaurant getRestaurant(String name){
        return restaurants.get(name);
    }
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        for (Map.Entry<String, Restaurant> entry : restaurants.entrySet()) {
            list.add(entry.getValue());
        }
        return list; 
    }

}
