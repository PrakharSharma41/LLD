import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingFloor {
    Map<VehicleType,List<ParkingSpot>>typeToSpot;
    int floorId;
    ParkingFloor(int floorId){
        this.floorId=floorId;
        typeToSpot=new ConcurrentHashMap<>();
    }
    public void addSpot(VehicleType vehicleType,int spotId){
        ParkingSpot spot=new ParkingSpot(spotId, floorId, vehicleType, null);
        typeToSpot.computeIfAbsent(vehicleType, k->new CopyOnWriteArrayList<>()).add(spot);
    }
    public void park(ParkingSpot spot,Vehicle vehicle){
        // synchronized(spot){
            spot.park(vehicle);
            System.out.println(vehicle+" parked at "+spot);    
        // }
    }
    public void unpark(ParkingSpot spot){
        // synchronized(spot){
            spot.unpark();
            System.out.println(spot+" is free");
        // }
    }
    public List<ParkingSpot> getAllSpots(){
        List<ParkingSpot>allSpots=new CopyOnWriteArrayList<>();
        for(List<ParkingSpot> s:typeToSpot.values()){
            allSpots.addAll(s);
        }
        return allSpots;
    }
}
