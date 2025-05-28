package singleFloor.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import singleFloor.Helper;

public class ParkingFloor {
    int id;
    public Map<VehicleType,List<ParkingSpot>>freeParkingSpots;
    Map<VehicleType,List<ParkingSpot>>allParkingSpots;

    public ParkingFloor(int id) {
        this.id = id;
        freeParkingSpots=new HashMap<>();
        allParkingSpots=new HashMap<>();
        Helper.setUp(freeParkingSpots,allParkingSpots);
    }
    public int park(Vehicle vehicle,ParkingSpot spot){
        spot.parkVehicle(vehicle);

        System.out.println("vehicle parked");
        return vehicle.getId();
    }
    public void removeVehicle(ParkingSpot spot){
        VehicleType type=spot.getVehicle().getVehicleType();
        freeParkingSpots.getOrDefault(type,new ArrayList<>()).add(spot);        
        spot.removeVehicle();
    }
    public void addParkingSpot(int spotId,VehicleType vehicleType){
        ParkingSpot spot=new ParkingSpot(spotId);
        freeParkingSpots.get(vehicleType).add(spot);
        allParkingSpots.get(vehicleType).add(spot);
    }
    public List<ParkingSpot> getFreeParkingSpot(VehicleType vehicleType){
        return freeParkingSpots.get(vehicleType);
    }
    
}
