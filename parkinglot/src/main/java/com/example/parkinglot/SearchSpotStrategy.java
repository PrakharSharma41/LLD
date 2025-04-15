package com.example.parkinglot;

import com.example.parkinglot.entities.Vehicle;

public interface SearchSpotStrategy {
    SpotLocation searchSpot(Vehicle vehicle);
}