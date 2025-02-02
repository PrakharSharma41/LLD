package RestaurantBooking.Controllers;

import RestaurantBooking.model.Restaurant;
import RestaurantBooking.model.Schedule;
import RestaurantBooking.model.User;
import RestaurantBooking.service.BookingService;

public class BookingController {
    BookingService bookingService;

    public BookingController() {
        bookingService=new BookingService();
    }
    public void createBooking(Restaurant restaurant,User user,int numberOfTablesBooked,Schedule schedule,int day){
        bookingService.createBooking(restaurant, user, numberOfTablesBooked, schedule, day);
    }
}
