package com.example.parkinglot;

import com.example.parkinglot.entities.ParkingFloor;
import com.example.parkinglot.entities.ParkingSpot;

public class SpotLocation {
    private ParkingFloor floor;
    private ParkingSpot spot;

    public SpotLocation(ParkingFloor floor, ParkingSpot spot) {
        this.floor = floor;
        this.spot = spot;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}