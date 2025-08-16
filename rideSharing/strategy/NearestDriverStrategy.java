package rideSharing.strategy;

import java.util.List;
import java.util.stream.Collectors;

import rideSharing.entity.Driver;
import rideSharing.entity.Location;
import rideSharing.enums.DriverStatus;
import rideSharing.enums.VehicleType;

public class NearestDriverStrategy implements DriverMatchingStrategy{

    @Override
    public List<Driver> findDrivers(List<Driver> allDriver, Location from,VehicleType type) {
        System.out.println(allDriver.size());
        return allDriver.stream().filter(driver->driver.getDriverStatus()==DriverStatus.ONLINE && driver.getVehicle().getVehicleType()==type)
        .sorted((a,b)->Double.compare(from.distance(a.getLocation()), from.distance(b.getLocation()))).collect(Collectors.toList());
    }
    
}
