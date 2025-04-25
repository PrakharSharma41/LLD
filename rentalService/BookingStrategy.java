import java.util.List;
import java.util.Optional;

public interface BookingStrategy {
    Vehicle selectVehicle(List<Vehicle> vehicles, BookingSlot slot);
}