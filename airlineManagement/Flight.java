import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Flight {
    String flightNumber;
    AirCraft airCraft;
    String source;
    String destination;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    Map<Integer,Seats>seats;
    Flight(AirCraft airCraft,String source,String destination,LocalDateTime departureTime,LocalDateTime arrivalTime){
        this.flightNumber=UUID.randomUUID().toString();
        this.airCraft=airCraft;
        this.source=source;
        this.destination=destination;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        seats=new HashMap<>();
    }    
    public void addSeats(Seats seat){
        seats.put(seat.seatNumber,seat);
    }
    public synchronized boolean isSeatAvailable(int seatNumber){
        if(seats.containsKey(seatNumber)==false)return false;
        return seats.get(seatNumber).isAvailable();
    }
    public void reserveSeat(int seatNumber){
        if(seats.containsKey(seatNumber)){
            seats.get(seatNumber).reserve();
        }
    }
    public void releaseSeat(int seatNumber){
        if(seats.containsKey(seatNumber)){
            seats.get(seatNumber).release();
        }
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public List<Seats> getSeats() {
        return new ArrayList<>(seats.values());
    }
    public Seats getSeat(int seatNumber) {
        return seats.get(seatNumber);
    }

    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

}
