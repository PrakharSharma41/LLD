import java.util.List;

public class SearchStrategyImpl implements SpotSearchStrategy{

    ParkingFloor[] floors;

    SearchStrategyImpl(ParkingFloor[] floors){
        this.floors=floors;
    }

    @Override
    public ParkingSpot findSpot(VehicleType vehicleType) {
        ParkingSpot spot=null;
        for(ParkingFloor floor:floors){
            for(ParkingSpot s:floor.getAllSpots()){
                if(s.isAvailable()){
                    if(vehicleType==VehicleType.CAR&&s.spotType==VehicleType.CAR)return s;
                    if(vehicleType==VehicleType.BIKE)return s;
                }
            }    
        }
        return spot;
    }
    
}
