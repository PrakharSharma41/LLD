package RestaurantBooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RestaurantBooking.model.Restaurant;
import RestaurantBooking.model.Schedule;
import RestaurantBooking.model.User;
import RestaurantBooking.model.UserType;

public class RestaurantService {
    HashMap<String,Restaurant>res;
    int maxDays;
    // RestaurantSearchStrategy 
    public RestaurantService(int maxDays) {
        res=new HashMap<>();
        this.maxDays=maxDays;
    }

    public Restaurant createRestaurant(User user,String name,String city,int numberOfTables){
        if(user.getUserType()==UserType.OWNER){
            Restaurant restaurant=new Restaurant(name, city);
            res.put(name,restaurant);
            setSchedules(restaurant,numberOfTables);
            return restaurant;
        }else{
            System.out.println("not owner");
            return null;
        }
        
    } 
    public void setSchedules(Restaurant restaurant,int numberOfTables){
        Map<Integer,List<Schedule>>availableSchedule=restaurant.getAvailableSchedule();
        for(int i=0;i<maxDays;i++){
            List<Schedule>schedules=new ArrayList<>();
            for(int j=1;j<24;j++){
                schedules.add(new Schedule(j, j+1,numberOfTables));
            }
            availableSchedule.put(i, schedules);
        }
        restaurant.setAvailableSchedule(availableSchedule);
    }
    public void showAvailableSchedules(String restaurantName){
        Restaurant restaurant=res.get(restaurantName);
        Map<Integer,List<Schedule>>availableSchedules=restaurant.getAvailableSchedule();
        for(Map.Entry<Integer,List<Schedule>>mp:availableSchedules.entrySet()){
            System.out.println("Day is "+mp.getKey());
            for(Schedule schedule:mp.getValue()){
                System.out.println("available schedule is "+schedule.getStartTime()+" to "+schedule.getEndTime()+" number of table available is "+schedule.getAvailableSeats());
            }
        }
        // show all bookings
    }
    public List<Restaurant>getAllRestaurants(){
        List<Restaurant>restaurants=new ArrayList<>();
        for(Restaurant r:res.values()){
            restaurants.add(r);
        }
        return restaurants;
    }

}
