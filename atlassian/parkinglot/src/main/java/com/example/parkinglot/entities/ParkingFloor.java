package com.example.parkinglot.entities;


import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import com.example.parkinglot.ParkingResult;

public class ParkingFloor {
    int floorId;
    public HashMap<VehicleType,ConcurrentLinkedDeque<ParkingSpot>>freeSpots;
    public HashMap<VehicleType,ConcurrentLinkedDeque<ParkingSpot>>allSpots;
    public HashMap<VehicleType, AtomicInteger> freeSpotsSize;

    public ParkingFloor(int floorId){
        this.floorId = floorId;
        freeSpots=new HashMap<>();
        freeSpotsSize=new HashMap<>();
        allSpots=new HashMap<>();
        for(VehicleType pst:VehicleType.values()){
            freeSpots.put(pst,new ConcurrentLinkedDeque<>());
            allSpots.put(pst,new ConcurrentLinkedDeque<>());
            freeSpotsSize.put(pst,new AtomicInteger(0));
        }        
    }
    public void addParkingSpots (String spotId,boolean isAvailable,VehicleType vehicleType){
        ParkingSpot spot=new ParkingSpot(spotId, isAvailable, vehicleType);
        allSpots.get(vehicleType).add(spot);
        freeSpots.get(spot.getVehicleType()).add(spot);
        freeSpotsSize.get(spot.getVehicleType()).addAndGet(1);
    }
    public ParkingResult parkVehicle(Vehicle vehicle, ParkingSpot spot){
        spot.setVehicle(vehicle);
        spot.setAvailable(false);
        System.out.println("parked");
        return new ParkingResult(201, spot, vehicle.getVehicleNumber());        
    }        
    
    public int removeVehicle(ParkingSpot spot){
        spot.setVehicle(null);
        spot.setAvailable(true);
        freeSpots.get(spot.getVehicleType()).add(spot);
        freeSpotsSize.get(spot.getVehicleType()).addAndGet(1);
        return 201;
    }
    int freeSpotsCount(VehicleType vehicleType){
        if(!freeSpotsSize.containsKey(vehicleType)) return 0;
        return freeSpotsSize.get(vehicleType).get();
    }    
}
