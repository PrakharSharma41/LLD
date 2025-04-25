import java.util.HashMap;
import java.util.Map;

public class VehicleController {
    // we can create vehicle service as well
    Map<Integer,Vehicle>idToVehicle;
    public VehicleController(){
        idToVehicle=new HashMap<>();
    }    

    public Vehicle getVehicleFromId(int id){
        return idToVehicle.get(id);
    }
    public Vehicle createVehicle( VehicleType vehicleType, int price){
        Vehicle vehicle=new Vehicle(vehicleType, (int)(Math.random()*100), price);
        idToVehicle.put(vehicle.getId(), vehicle);
        return vehicle;
    }
    public void bookVehicle(Vehicle vehicle,BookingSlot slot){
        vehicle.book(slot);
    }
}
