package com.example.parkinglot.entities;

import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
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

    // for thread safe
    // private final ReentrantLock lock = new ReentrantLock();

    // public boolean tryPark(Vehicle vehicle) {
    //     if (lock.tryLock()) {
    //         try {
    //             if (!isAvailable) return false;
    //             this.vehicle = vehicle;
    //             this.isAvailable = false;
    //             return true;
    //         } finally {
    //             lock.unlock();
    //         }
    //     }
    //     return false;
    // }

    // public boolean tryFree() {
    //     if (lock.tryLock()) {
    //         try {
    //             if (isAvailable) return false; // already free
    //             this.vehicle = null;
    //             this.isAvailable = true;
    //             return true;
    //         } finally {
    //             lock.unlock();
    //         }
    //     }
    //     return false;
    // }    
}
