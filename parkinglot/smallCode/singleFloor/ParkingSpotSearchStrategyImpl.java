package singleFloor;

import singleFloor.entities.ParkingFloor;
import singleFloor.entities.ParkingSpot;
import singleFloor.entities.VehicleType;

public class ParkingSpotSearchStrategyImpl implements ParkingSpotSearchStrategy{

    ParkingFloor parkingFloor;
    ParkingSpotSearchStrategyImpl(ParkingFloor parkingFloor){
        this.parkingFloor=parkingFloor;
    }
    @Override
    public ParkingSpot findParkingSpot(VehicleType vehicleType) {
        ParkingSpot spot=null;
        if(parkingFloor.freeParkingSpots!=null&&parkingFloor.getFreeParkingSpot(vehicleType)!=null){
            for(ParkingSpot s:parkingFloor.getFreeParkingSpot(vehicleType)){
                if(s.isAvailable()){
                    spot=s;break;
                }
            }
        }
        return spot;
    }
    
}
