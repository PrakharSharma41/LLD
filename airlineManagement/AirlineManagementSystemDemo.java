import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AirlineManagementSystemDemo {
    public static void main(String[] args) {
        AirlineManagementSystem airlineManagementSystem = new AirlineManagementSystem();

        // Create passengers
        Passenger passenger1 = airlineManagementSystem.addPassenger("John Doe", "john@example.com");
        Passenger passenger2 = airlineManagementSystem.addPassenger("John Smith", "smith@example.com");

        // Create aircrafts
        AirCraft aircraft1 = airlineManagementSystem.addAircraft("A001", "Boeing 747", 300);
        AirCraft aircraft2 = airlineManagementSystem.addAircraft("A002", "Airbus A380", 500);

        // Create flights
        LocalDateTime departureTime1 = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivalTime1 = departureTime1.plusHours(2);
        Flight flight1 = airlineManagementSystem.addFlight("New York", "London", departureTime1, arrivalTime1, aircraft1.getTailNumber());

        airlineManagementSystem.addSeats(flight1, SeatType.ECONOMY, 25);
        airlineManagementSystem.addSeats(flight1, SeatType.ECONOMY, 26);
        airlineManagementSystem.addSeats(flight1, SeatType.ECONOMY, 27);
        airlineManagementSystem.addSeats(flight1, SeatType.ECONOMY, 28);

        LocalDateTime departureTime2 = LocalDateTime.now().plusDays(3);
        LocalDateTime arrivalTime2 = departureTime2.plusHours(5);
        Flight flight2 = airlineManagementSystem.addFlight("Paris", "Tokyo", departureTime2, arrivalTime2, aircraft2.getTailNumber());

        // Search flights
        List<Flight> searchResults = airlineManagementSystem.searchFlights("New York", "London", LocalDate.now().plusDays(1));
        System.out.println("Search Results:");
        for (Flight flight : searchResults) {
            System.out.println("Flight: " + flight.getFlightNumber() + " - " + flight.getSource() + " to " + flight.getDestination());
        }

        // Book a flight
        Booking booking = airlineManagementSystem.bookFlight(flight1.getFlightNumber(), passenger1.getId(), Arrays.asList(25,26), 100);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
        } else {
            System.out.println("Booking failed.");
        }

        // Cancel a booking
        // airlineManagementSystem.cancelBooking(booking.getId());
        // System.out.println("Booking cancelled.");        
        booking = airlineManagementSystem.bookFlight(flight1.getFlightNumber(), passenger1.getId(), Arrays.asList(26,27), 100);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
        } else {
            System.out.println("Booking failed.");
        }

    }
}
