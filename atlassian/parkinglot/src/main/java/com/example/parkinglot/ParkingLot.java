package com.example.parkinglot;

import com.example.parkinglot.entities.ParkingFloor;
import com.example.parkinglot.entities.ParkingSpot;
import com.example.parkinglot.entities.Vehicle;
import com.example.parkinglot.entities.VehicleType;

public class ParkingLot {
    private String parkingLotName;
    ParkingFloor floors[];
    SearchManager searchManager;
    SearchSpotStrategy strategy;
    public ParkingLot(String parkingLotName) {
        this.parkingLotName=parkingLotName;
        floors=new ParkingFloor[1];
        floors[0]=new ParkingFloor(0);
        searchManager=new SearchManager();
        strategy=new SearchSpotStrategyImpl(floors);
    }
    public void createParkingSpots(int floorId,String spotId,boolean isAvailable,VehicleType vehicleType ){
        floors[floorId].addParkingSpots(spotId, isAvailable, vehicleType);
    }
    public ParkingResult park(Vehicle vehicle){
        SpotLocation spotLocation = strategy.searchSpot(vehicle);
        if (spotLocation != null) {
            ParkingFloor floor = spotLocation.getFloor();
            ParkingSpot spot = spotLocation.getSpot();
            ParkingResult result = floor.parkVehicle(vehicle, spot);
            if(result != null && result.getStatus() == 201){
                searchManager.index(result);
                return result;
            }
        }
        return new ParkingResult(404, null, "invalid");
    }
    public int removeVehicle(String ticketId){
        ParkingResult search = searchManager.searchVehicle( ticketId);
        if(search==null||search.getStatus()>=400)return 404;
        ParkingSpot spot=search.getParkingSpot();
        return floors[0].removeVehicle(spot);
    }
    public ParkingSpot searchVehicle(String ticketId){
        return searchManager.searchVehicle( ticketId).getParkingSpot();
    }
}
