import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearch {
    public List<Flight>flights=new ArrayList<>();
    public void addFlight(Flight flight) {
        flights.add(flight);
    }    
    public List<Flight>searchFlights(String source, String destination,LocalDate date){
        return flights.stream().filter(flight->flight.getSource().equals(source) && flight.getDestination().equals(destination)&&flight.departureTime.toLocalDate().equals(date)).collect(Collectors.toList());
    }
}
