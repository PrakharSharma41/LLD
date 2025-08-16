package rideSharing.entity;

import rideSharing.enums.DriverStatus;

public class Driver extends User{
    @Override
    public String toString() {
        return "Driver [driverStatus=" + driverStatus + ", id=" + id + ", location=" + location + "]";
    }
    Vehicle vehicle;
    DriverStatus driverStatus;
    public Driver(String id, String contact, String name,Vehicle vehicle,Location location) {
        super(id, contact, name,location);
        this.driverStatus=DriverStatus.ONLINE;
        this.vehicle=vehicle;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public DriverStatus getDriverStatus() {
        return driverStatus;
    }
    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }



}
