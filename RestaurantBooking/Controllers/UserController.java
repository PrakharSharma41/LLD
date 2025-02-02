package RestaurantBooking.Controllers;

import RestaurantBooking.model.User;
import RestaurantBooking.model.UserType;
import RestaurantBooking.service.UserService;


public class UserController {
    UserService userService;

    public UserController() {
        userService=new UserService();
    }
    public User createUser(String name,UserType type){
        return userService.createUser(name, type);
    }
}
