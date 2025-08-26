public class ParkingSpot{
    int spotId;
    int floorId;
    VehicleType spotType;
    Vehicle vehicle;
    
    public ParkingSpot(int spotId, int floorId, VehicleType spotType, Vehicle vehicle) {
        this.spotId = spotId;
        this.floorId = floorId;
        this.spotType = spotType;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot [spotId=" + spotId + ", floorId=" + floorId + "]";
    }

    boolean isAvailable(){
        return vehicle==null;
    }
    void park(Vehicle vehicle){
        this.vehicle=vehicle;        
    }
    void unpark(){
        vehicle=null;
    }
}