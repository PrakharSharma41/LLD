package rideSharing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rideSharing.entity.Driver;
import rideSharing.entity.Location;
import rideSharing.entity.Rider;
import rideSharing.entity.Trip;
import rideSharing.entity.Vehicle;
import rideSharing.enums.DriverStatus;
import rideSharing.enums.TripStatus;
import rideSharing.enums.VehicleType;
import rideSharing.strategy.DriverMatchingStrategy;
import rideSharing.strategy.FlatRateStrategy;
import rideSharing.strategy.NearestDriverStrategy;
import rideSharing.strategy.PricingStrategy;

public class RideSharingService {
    Map<String,Rider>riderMap;
    Map<String,Driver>driverMap;
    Map<String,Trip>tripMap;
    PricingStrategy pricingStrategy;
    DriverMatchingStrategy driverMatchingStrategy;
    public RideSharingService(){
        riderMap=new HashMap<>();
        driverMap=new HashMap<>();
        tripMap=new HashMap<>();
        driverMatchingStrategy=new NearestDriverStrategy();
        pricingStrategy=new FlatRateStrategy();
    }
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }
    public Rider registerRider(String id,String name, String contact,Location location) {
        Rider rider = new Rider(id, contact,name,location);
        riderMap.put(rider.getId(), rider);
        return rider;
    }

    public Driver registerDriver(String id,String name, String contact, Vehicle vehicle, Location initialLocation) {
        Driver driver = new Driver(id, contact,name, vehicle, initialLocation);
        driverMap.put(driver.getId(), driver);
        return driver;
    }    
    public Trip requestTrip(String riderId,Location from,Location to,VehicleType type){
        Rider rider=riderMap.get(riderId);
        if(rider==null)return null;
       
        List<Driver>availableDriver=driverMatchingStrategy.findDrivers(List.copyOf(driverMap.values()), from, type);
        if(availableDriver==null||availableDriver.size()==0)return null;
        double fare=pricingStrategy.calculateFare(from, to, type);
        Trip trip=new Trip(riderId, rider, null, from, to, TripStatus.REQUESTED, fare);
        tripMap.put(trip.getId(), trip);
        for(Driver d:availableDriver)
        System.out.println(d);
        // send push notificaion to drivers
        return trip;
    }
    public boolean acceptRide(String driverId,String tripId){
        Driver driver=driverMap.get(driverId);
        Trip trip=tripMap.get(tripId);
        if(driver==null||trip==null)return false;
        driver.setDriverStatus(DriverStatus.IN_TRIP);
        trip.setDriver(driver);
        trip.setTripStatus(TripStatus.IN_PROGRESS);
        return true;
    }
    public boolean startTrip(String tripId){
        Trip trip=tripMap.get(tripId);
        if(trip!=null){
            Driver driver=trip.getDriver();
            driver.setDriverStatus(DriverStatus.IN_TRIP);
            return true;    
        }
        return false;
    }
    public boolean endTrip(String tripId){
        Trip trip=tripMap.get(tripId);
        if(trip!=null){
            Driver driver=trip.getDriver();
            driver.setDriverStatus(DriverStatus.ONLINE);
            trip.setTripStatus(TripStatus.COMPLETED);
            driver.addTripHistory(trip);
            Rider rider=trip.getRider();
            rider.addTripHistory(trip);
            return true;
        }
        return false;
    }

}
