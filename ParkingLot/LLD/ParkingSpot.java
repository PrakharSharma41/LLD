package ParkingLot.LLD;

class ParkingSpot {
    private String spotId;
    private boolean isAvailable;
    private Vehicle vehicle;
    public  VehicleType vehicleType;

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "ParkingSpot [spotId=" + spotId + ", isAvailable=" + isAvailable + ", vehicleType=" + vehicleType + "]";
    }

    public ParkingSpot(String spotId, boolean isAvailable, VehicleType parkingSpotType) {
        this.spotId = spotId;
        this.isAvailable = isAvailable;
        this.vehicleType = parkingSpotType;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getSpotId() {
        return spotId;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public void freeSpot() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}