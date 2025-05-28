package singleFloor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import singleFloor.entities.ParkingSpot;
import singleFloor.entities.VehicleType;

public class Helper {
    public static void setUp(Map<VehicleType,List<ParkingSpot>>parkingSpots,Map<VehicleType,List<ParkingSpot>>allSpots){
        for(VehicleType vt:VehicleType.values()){
            parkingSpots.putIfAbsent(vt, new ArrayList<ParkingSpot>());
            allSpots.putIfAbsent(vt, new ArrayList<ParkingSpot>());
        }
    }    
}
