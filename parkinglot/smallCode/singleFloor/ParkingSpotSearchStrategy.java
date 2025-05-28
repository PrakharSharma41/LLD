package singleFloor;

import singleFloor.entities.ParkingFloor;
import singleFloor.entities.ParkingSpot;
import singleFloor.entities.VehicleType;

public interface ParkingSpotSearchStrategy {
    public ParkingSpot findParkingSpot(VehicleType vehicleType);
}
