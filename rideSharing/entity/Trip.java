package rideSharing.entity;

import rideSharing.enums.TripStatus;

public class Trip {
    String id;
    Rider rider;
    Driver driver;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    Location fromLocation;
    Location toLocation;
    TripStatus tripStatus;
    public Rider getRider() {
        return rider;
    }
    public void setRider(Rider rider) {
        this.rider = rider;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public Location getFromLocation() {
        return fromLocation;
    }
    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }
    public Location getToLocation() {
        return toLocation;
    }
    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }
    public TripStatus getTripStatus() {
        return tripStatus;
    }
    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
    public double getFare() {
        return fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }
    double fare;
    public Trip(String id,Rider rider, Driver driver, Location fromLocation, Location toLocation, TripStatus tripStatus,
            double fare) {
        this.id=id;
        this.rider = rider;
        this.driver = driver;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripStatus = tripStatus;
        this.fare = fare;
    }

}
