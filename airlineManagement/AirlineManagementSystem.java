import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirlineManagementSystem {
    private final Map<String, Flight> flights;
    private final Map<String, AirCraft> aircrafts;
    private final Map<String, Passenger> passengers;
    private final FlightSearch flightSearch;
    private final BookingManager bookingManager;
    private final PaymentProcessor paymentProcessor;
    public AirlineManagementSystem() {
        flights = new HashMap<>();
        aircrafts = new HashMap<>();
        passengers = new HashMap<>();
        flightSearch = new FlightSearch();
        bookingManager = new BookingManager();
        paymentProcessor = PaymentProcessor.getInstance();
    }
    public Passenger addPassenger(String name, String email) {
        Passenger passenger = new Passenger(name, email);
        passengers.put(passenger.getId(), passenger);
        return passenger;
    }

    public AirCraft addAircraft(String tailNumber, String model, int totalSeats) {
        AirCraft aircraft = new AirCraft(tailNumber, model, totalSeats);
        aircrafts.put(tailNumber, aircraft);
        return aircraft;
    }    
    public Flight addFlight(String source, String destination, LocalDateTime departure,
                            LocalDateTime arrival, String aircraftNumber) {
        AirCraft aircraft = aircrafts.get(aircraftNumber);
        Flight flight = new Flight(aircraft,source, destination, departure, arrival);
        flights.put(flight.getFlightNumber(), flight);
        flightSearch.addFlight(flight);
        return flight;
    }    
    public void addSeats(Flight flight,SeatType seatType,int seatNumber){
        flight.addSeats(new Seats(seatType, SeatStatus.AVAILABLE, seatNumber));
    }
    
    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flightSearch.searchFlights(source, destination, date);
    }

    public Booking bookFlight(String flightNumber, String passengerId, List<Integer> seatNumber, double price) {
        Flight flight = flights.get(flightNumber);
        Passenger passenger = passengers.get(passengerId);
        List<Seats> selectedSeats = new ArrayList<>();
        for (Integer number : seatNumber) {
            Seats seat = flight.getSeat(number);
            if (seat == null || !seat.isAvailable()) {
                System.out.println("Seat " + number + " is not available.");
                return null; // Conflict: One or more seats already booked
            }
            selectedSeats.add(seat);
        }        return bookingManager.createBooking(passenger,flight, selectedSeats);
    }

    public void cancelBooking(String bookingNumber) {
        bookingManager.cancelBooking(bookingNumber);
    }

    public void processPayment(Payment payment) {
        paymentProcessor.processPayment(payment);
    }    
}
