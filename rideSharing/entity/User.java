package rideSharing.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public List<Trip> getTripHistory() {
        return tripHistory;
    }
    public void addTripHistory(Trip trip) {
        tripHistory.add(trip);
    }
    String name;
    String id;
    String contact;
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    List<Trip>tripHistory;
    Location location;
    User(String id,String contact,String name,Location location){
        this.id=id;
        this.contact=contact;
        this.name=name;
        tripHistory=new ArrayList<>();
        this.location=location;
    }
}
