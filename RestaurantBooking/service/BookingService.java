package RestaurantBooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RestaurantBooking.model.Booking;
import RestaurantBooking.model.Restaurant;
import RestaurantBooking.model.Schedule;
import RestaurantBooking.model.User;

public class BookingService {
    Map<User,List<Booking>>userBookings;

    public BookingService() {
        userBookings=new HashMap<>();
    }
    public Booking createBooking(Restaurant restaurant,User user,int numberOfTablesBooked, Schedule schedule,int day){
        System.out.println("khabskdan");
        if(bookingPossible(restaurant,numberOfTablesBooked,schedule,day)){
            System.out.println("possible");
            Booking booking=new Booking(user, restaurant, numberOfTablesBooked, schedule,day);
            if(userBookings.containsKey(user)==false)userBookings.put(user, new ArrayList<>());
            userBookings.get(user).add(booking);
            return booking;    
        }
        return null;
    }
    private boolean bookingPossible(Restaurant restaurant,int numberOfTablesBooked,Schedule schedule,int day){
        List<Schedule>schedules=restaurant.getAvailableSchedule().get(day);
        System.out.println("schedule to be booked is "+schedule);
        for(Schedule sc:schedules){
            System.out.println("schedule is "+sc);
            if(sc.getStartTime()==schedule.getStartTime()&&sc.getEndTime()==schedule.getEndTime()&&sc.getAvailableSeats()>=numberOfTablesBooked){
                sc.setAvailableSeats(numberOfTablesBooked);
                System.out.println("seat booked");
                return true;
            }
        }
        return false;
    }
    
}
