package RestaurantBooking.model;

public class Booking {
    User user;
    Restaurant restaurant;
    int numberOfTablesBooked, day;
    Schedule schedule;
    public Booking(User user, Restaurant restaurant, int numberOfTablesBooked, Schedule schedule,int day) {
        this.user = user;
        this.restaurant = restaurant;
        this.numberOfTablesBooked = numberOfTablesBooked;
        this.schedule = schedule;
        this.day=day;
    }
    
}
