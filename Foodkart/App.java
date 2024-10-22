package Foodkart;
import java.util.*;
public class App {
    public static void main(String[] args) {
        List<Integer>ans=new ArrayList<>();
        ans.add(null);
        FoodKart foodKart=new FoodKart();
        foodKart.registerCustomer("user1", "male", "12345", "302012");
        foodKart.registerCustomer("user2", "male", "15432", "302012");
        foodKart.registerOwner("owner", "male", "15432", "302012");
        foodKart.logIn(foodKart.getOwners().get(0));
        foodKart.registerRestaurant("res1", new ArrayList<String>(Arrays.asList("302012")), new Food("food",12,1), 21, null);        
        foodKart.updateQuantity("res1", 12);
        foodKart.logout();
        foodKart.logIn(foodKart.getCustomers().get(0));
        System.out.println(foodKart.restaurants.get(0).getDishManager().getFood().get(0).getInitialQuantity());
        foodKart.createOrder(foodKart.restaurants.get(0), 12);
        List<Restaurant> res=foodKart.showRestaurant(ResGetterType.RATING);
        System.out.println(res.get(0));
        foodKart.rate_restaurant(res.get(0), 1);
        System.out.println(res.get(0));
        foodKart.rate_restaurant(res.get(0), 5);
        System.out.println(res.get(0));
        // foodKart.rate_restaurant("res1", 12);
        System.out.println();
    }
}
