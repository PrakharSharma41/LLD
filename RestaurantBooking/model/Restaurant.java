package RestaurantBooking.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    String restaurantName;
    String city;
    // int numberOfTables;
    Map<Integer,List<Schedule>>availableSchedule; // day to schedule mapping
    public Restaurant(String restaurantName,String city) {
        this.restaurantName = restaurantName;
        this.city=city;
        availableSchedule=new HashMap<>();
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Map<Integer, List<Schedule>> getAvailableSchedule() {
        return availableSchedule;
    }

    public void setAvailableSchedule(Map<Integer, List<Schedule>> availableSchedule) {
        this.availableSchedule = availableSchedule;
    }
    public Map<Integer,List<Schedule>>showAvailableSchedules(){
        return availableSchedule;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantName=" + restaurantName + ", city=" + city +" ]";
    }
    
}
