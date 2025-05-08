import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingManager {
    Map<String,Booking>bookingMap;
    BookingManager(){
        bookingMap=new HashMap<>();
    }
    public synchronized Booking createBooking(Passenger passenger,Flight flight,List<Seats>bookedSeats){
        for (Seats seat : bookedSeats) {
            if (!seat.isAvailable()) {
                System.out.println("Seat " + seat.seatNumber + " is already booked.");
                return null;
            }
        }        
        String bookingNumber = UUID.randomUUID().toString();
        bookedSeats.stream().forEach(seat->seat.reserve());
        Booking booking = new Booking(bookingNumber, passenger, bookedSeats,flight);        
        System.out.println("booking created");
        bookingMap.put(bookingNumber, booking);
        return booking;
    }
    public void cancelBooking(String bookingNumber){
        if(bookingMap.containsKey(bookingNumber)){
            bookingMap.get(bookingNumber).cancelBooking();
        }
    }
}
