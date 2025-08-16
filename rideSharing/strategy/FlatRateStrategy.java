package rideSharing.strategy;

import rideSharing.entity.Location;
import rideSharing.enums.VehicleType;

public class FlatRateStrategy implements PricingStrategy{
    private static final double BASE_FARE = 5.0;
    private static final double FLAT_RATE = 1.5;
    @Override
    public double calculateFare(Location from, Location to, VehicleType type) {
        double distance = from.distance(to);
        return BASE_FARE + distance * FLAT_RATE;

    }
    
}
