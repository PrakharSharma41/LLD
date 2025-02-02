package RestaurantBooking.service;

import java.util.HashMap;
import java.util.Map;

import RestaurantBooking.model.User;
import RestaurantBooking.model.UserType;

public class UserService {
    Map<String,User>users;
    
    public UserService() {
        users=new HashMap<>();
    }

    public User createUser(String name,UserType userType){
        User user=new User(name, userType);
        users.put(name,user);
        return user;
    }
}
