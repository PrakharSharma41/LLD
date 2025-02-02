package RestaurantBooking;

import RestaurantBooking.Controllers.BookingController;
import RestaurantBooking.Controllers.RestaurantController;
import RestaurantBooking.Controllers.UserController;
import RestaurantBooking.model.Restaurant;
import RestaurantBooking.model.Schedule;
import RestaurantBooking.model.User;
import RestaurantBooking.model.UserType;

public class Main {
    public static void main(String[] args) {
        RestaurantController restaurantController=new RestaurantController();
        UserController userController=new UserController();
        BookingController bookingController=new BookingController();
        User owner=userController.createUser("ADMIN", UserType.OWNER);
        Restaurant restaurant1=restaurantController.createRestaurant(owner, "res1", "city1", 10);
        Restaurant restaurant2=restaurantController.createRestaurant(owner, "res2", "city2", 10);
        Restaurant restaurant3=restaurantController.createRestaurant(owner, "res3", "city3", 10);
        Restaurant restaurant4=restaurantController.createRestaurant(owner, "res4", "city4", 10);
        Restaurant restaurant5=restaurantController.createRestaurant(owner, "res5", "city5", 10);
        RestaurantSearchStrategy strategy=new RestaurantSearchImpl();
        restaurantController.showAvailableSchedules("res5");
        // restaurantController.searchRestaurant(strategy);
        bookingController.createBooking(restaurant5, owner, 5, new Schedule(2, 3, 5), 0);
        // restaurantController.showAvailableSchedules(restaurant.getRestaurantName());
        restaurantController.showAvailableSchedules("res5");
    }
}
