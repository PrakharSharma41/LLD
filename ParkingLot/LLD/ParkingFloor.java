package ParkingLot.LLD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingFloor {
    int floorId;
    private HashMap<VehicleType,ConcurrentLinkedDeque<ParkingSpot>>freeSpots;
    private List<ParkingSpot>allSpots;
    private HashMap<VehicleType, AtomicInteger> freeSpotsSize;

    public ParkingFloor(int floorId){
        this.floorId = floorId;
        freeSpots=new HashMap<>();
        freeSpotsSize=new HashMap<>();
        allSpots=new ArrayList<>();
        for(VehicleType pst:VehicleType.values()){
            freeSpots.put(pst,new ConcurrentLinkedDeque<>());
            freeSpotsSize.put(pst,new AtomicInteger(0));
        }        
    }
    public void addParkingSpots (String spotId,boolean isAvailable,VehicleType vehicleType){
        ParkingSpot spot=new ParkingSpot(spotId, isAvailable, vehicleType);
        this.allSpots.add(spot);
        freeSpots.get(spot.getVehicleType()).add(spot);
        freeSpotsSize.get(spot.getVehicleType()).addAndGet(1);
    }
    // private final ReentrantLock lock = new ReentrantLock();
    public ParkingResult parkVehicle(Vehicle vehicle) {
        AtomicInteger size = freeSpotsSize.get(vehicle.getVehicleType());
        VehicleType vehicleType = vehicle.getVehicleType();
    
        while (true) {
            int currentSize = size.get();
            if (currentSize <= 0) {
                return new ParkingResult(404, null, "invalid");
            }
            
            // Reserve spot atomically
            if (size.compareAndSet(currentSize, currentSize - 1)) {
                ParkingSpot spot = freeSpots.get(vehicleType).poll(); // thread-safe
                if (spot != null) {
                    spot.setVehicle(vehicle);
                    System.out.println(vehicle + " parked");
                    return new ParkingResult(201, spot, vehicle.getVehicleNumber());
                }
            }
        }
    }
    
    
    public int removeVehicle(ParkingSpot spot){
        spot.setVehicle(null);
        spot.setAvailable(false);
        freeSpots.get(spot.getVehicleType()).add(spot);
        freeSpotsSize.get(spot.getVehicleType()).addAndGet(1);
        return 201;
    }
    int freeSpotsCount(VehicleType vehicleType){
        if(!freeSpotsSize.containsKey(vehicleType)) return 0;
        return freeSpotsSize.get(vehicleType).get();
    }    
}
