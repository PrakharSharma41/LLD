
public class Vehicle {
    String id;
    VehicleType vehicleType;
    public Vehicle(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }
    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", vehicleType=" + vehicleType + "]";
    }
}
