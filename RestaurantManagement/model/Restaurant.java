package RestaurantManagement.model;

import java.util.List;

public class Restaurant {
    String restaurantName;
    List<String> pincodes;
    Food foodItem;
    Rating rating;
    public Restaurant(String restaurantName, List<String> pincodes, Food foodItem) {
        this.restaurantName = restaurantName;
        this.pincodes = pincodes;
        this.foodItem = foodItem;
        this.rating=new Rating();
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public List<String> getPincodes() {
        return pincodes;
    }
    public void setPincodes(List<String> pincodes) {
        this.pincodes = pincodes;
    }
    public Food getFoodItem() {
        return foodItem;
    }
    public void setFoodItem(Food foodItem) {
        this.foodItem = foodItem;
    }
    public void updateRating(int rate,String comment){
        rating.addRatingAndComment(comment, rate);
    }
    public boolean isLocationServiceAble(String pinCode) {
        return pincodes.contains(pinCode);
    }

    public boolean isEnoughQuantityAvailable() {
        return foodItem.getQuantity()>0;
    }
    public Double getRestaurantRating() {
        return rating.getAverageRating();
    }

}
