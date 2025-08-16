package rideSharing.strategy;

import rideSharing.entity.Location;
import rideSharing.enums.VehicleType;

public interface PricingStrategy {
    double calculateFare(Location from,Location to,VehicleType type);
}
