import java.util.List;

public class Booking {
    String id;
    Passenger passenger;
    List<Seats>bookedSeats;
    Flight flight;
    Booking(String id,Passenger passenger,List<Seats>bookedSeats,Flight flight){
        this.id=id;this.passenger=passenger;this.bookedSeats=bookedSeats;this.flight=flight;
    }
    public void cancelBooking(){
        bookedSeats.stream().forEach(seat->seat.release());
    }
    public String getId() {
        return id;
    }
    
}
