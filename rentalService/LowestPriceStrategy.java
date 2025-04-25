import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LowestPriceStrategy implements BookingStrategy {
    @Override
    public Vehicle selectVehicle(List<Vehicle> vehicles, BookingSlot slot) {

        return vehicles.stream()
                .filter(v -> v.isAvailable(slot))
                .min(Comparator.comparingInt(Vehicle::getPrice)).orElse(null);
    }
}
