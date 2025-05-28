package singleFloor.entities;

public class ParkingSpot {
    int spotId;
    Vehicle vehicle;
    boolean isAvailable;
    int floorId; // use this to make multi floor extension easier
    public ParkingSpot(int spotId) {
        this.spotId = spotId;
        isAvailable=true;
    }
    public int getSpotId() {
        return spotId;
    }
    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        isAvailable=false;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable() {
        vehicle=null;
        isAvailable=true;
    }
    public void parkVehicle(Vehicle vehicle){
        this.vehicle=vehicle;
        isAvailable=false;
    }
    public void removeVehicle(){
        isAvailable=true;
        this.vehicle=null;
    }
}
