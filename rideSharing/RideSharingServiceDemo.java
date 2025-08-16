package rideSharing;

import rideSharing.entity.Location;
import rideSharing.entity.Rider;
import rideSharing.entity.Trip;
import rideSharing.entity.Vehicle;
import rideSharing.enums.VehicleType;

public class RideSharingServiceDemo {
    public static void main(String[] args) {
        RideSharingService service=new RideSharingService();
        service.registerRider("r1", "rider1", "123456", new Location(0.0, 0.0));
        service.registerDriver("d1", "driver1", "87123", new Vehicle(VehicleType.SEDAN, "asdae"),new Location(1.0, 1.0));
        service.registerDriver("d2", "driver2", "5678", new Vehicle(VehicleType.SUV, "lkj"),new Location(2.0, 2.0));
        service.registerDriver("d3", "driver3", "98765", new Vehicle(VehicleType.SEDAN, "wsdfv"),new Location(10.0, 10.0));
        Trip trip1=service.requestTrip("r1", new Location(0.0, 0.0), new Location(110.0, 110.0), VehicleType.SEDAN);   
        service.acceptRide("d3", trip1.getId());
       
        Trip trip2=service.requestTrip("r1", new Location(0.0, 0.0), new Location(110.0, 110.0), VehicleType.SEDAN);   
        // service.acceptRide("d3", trip1.getId());

    }
}
