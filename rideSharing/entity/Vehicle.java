package rideSharing.entity;

import rideSharing.enums.VehicleType;

public class Vehicle {
    VehicleType vehicleType;
    String number;
    public Vehicle(VehicleType vehicleType, String number) {
        this.vehicleType = vehicleType;
        this.number = number;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    
}
