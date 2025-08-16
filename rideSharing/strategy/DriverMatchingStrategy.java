package rideSharing.strategy;

import java.util.List;


import rideSharing.entity.Driver;
import rideSharing.entity.Location;
import rideSharing.enums.VehicleType;

public interface DriverMatchingStrategy {
    List<Driver> findDrivers(List<Driver>allDriver, Location from,VehicleType type);
}
