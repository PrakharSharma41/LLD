
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    VehicleType vehicleType;
    int id;
    List<BookingSlot>bookingSlots;
    int price;
    public Vehicle(VehicleType vehicleType, int id, int price) {
        this.vehicleType = vehicleType;
        this.id = id;
        this.price = price;
        bookingSlots=new ArrayList<>();
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    @Override
    public String toString() {
        return "Vehicle [vehicleType=" + vehicleType + ", id=" + id + ", price=" + price + "]";
    }
    public int getId() {
        return id;
    }
    public List<BookingSlot> getBookingSlots() {
        return bookingSlots;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isAvailable(BookingSlot slot) {
        return bookingSlots.stream().noneMatch(b -> b.overlaps(slot));
    }      
    public void book(BookingSlot slot) {
        bookingSlots.add(slot);
    }
}
