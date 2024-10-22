package Foodkart;
import java.util.*;


public class Restaurant {
    String restaurantName;
    DishManager dishManager;
    List<String>pincodes;
    User user;
    RateManager rateManager;
    public Restaurant() {
        dishManager=new DishManager(null);
        rateManager=new RateManager();
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public DishManager getDishManager() {
        return dishManager;
    }
    public void setDishManager(DishManager dishManager) {
        this.dishManager = dishManager;
    }
    public List<String> getPincodes() {
        return pincodes;
    }
    public void setPincodes(List<String> pincodes) {
        this.pincodes = pincodes;
    }
    public User getUser() {
        return user;
    }
    @Override
    public String toString() {
        return "Restaurant [restaurantName=" + restaurantName + ", dishManager=" + dishManager + ", pincodes="
                + pincodes + ", user=" + user + ", rateManager=" + rateManager + "]";
    }
    public void setUser(User user) {
        this.user = user;
    }
    public RateManager getRateManager() {
        return rateManager;
    }
    public void setRateManager(RateManager rateManager) {
        this.rateManager = rateManager;
    }
    
}
