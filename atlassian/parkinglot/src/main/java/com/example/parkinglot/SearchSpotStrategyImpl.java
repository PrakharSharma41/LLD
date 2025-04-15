package com.example.parkinglot;

import java.util.concurrent.ConcurrentLinkedDeque;

import com.example.parkinglot.entities.ParkingFloor;
import com.example.parkinglot.entities.ParkingSpot;
import com.example.parkinglot.entities.Vehicle;

public class SearchSpotStrategyImpl implements SearchSpotStrategy {
    ParkingFloor[] floors;

    public SearchSpotStrategyImpl(ParkingFloor[] floors) {
        this.floors = floors;
    }

    // @Override
    // public SpotLocation searchSpot(Vehicle vehicle) {
    //     for (ParkingFloor floor : floors) {
    //         ConcurrentLinkedDeque<ParkingSpot> spots = floor.freeSpots.get(vehicle.getVehicleType());
    //         if (spots != null && !spots.isEmpty()) {
    //             ParkingSpot spot = spots.peek(); // Just return it — park() will poll it
    //             return new SpotLocation(floor, spot);
    //         }
    //     }
    //     return null;
    // }
    @Override
    public SpotLocation searchSpot(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ConcurrentLinkedDeque<ParkingSpot> spots = floor.freeSpots.get(vehicle.getVehicleType());
            if (spots != null) {
                ParkingSpot spot = spots.poll(); // ✅ thread-safe remove
                if (spot != null) {
                    floor.freeSpotsSize.get(vehicle.getVehicleType()).decrementAndGet();
                    return new SpotLocation(floor, spot);
                }
            }
        }
        return null;
    }    
}