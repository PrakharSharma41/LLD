package ParkingLot.LLD;

public class ParkingLot {
    private String parkingLotName;
    ParkingFloor floors[];
    SearchManager searchManager;
    public ParkingLot(String parkingLotName) {
        this.parkingLotName=parkingLotName;
        floors=new ParkingFloor[1];
        floors[0]=new ParkingFloor(0);
        searchManager=new SearchManager();
    }
    public void createParkingSpots(int floorId,String spotId,boolean isAvailable,VehicleType vehicleType ){
        floors[floorId].addParkingSpots(spotId, isAvailable, vehicleType);
    }
    public ParkingResult park(Vehicle vehicle){
        for(ParkingFloor floor:floors){
            ParkingResult result=floor.parkVehicle(vehicle);
            if(result!=null && result.getStatus()==201){
                searchManager.index(result);
                // System.out.println("remaining slots are "+freeSpots(0, vehicle.getVehicleType()));
                return result;
            }            
        }
        return new ParkingResult(404, null, "invalid");
    }
    public int removeVehicle(String ticketId){
        ParkingResult search = searchManager.searchVehicle( ticketId);
        if(search==null||search.getStatus()>=400)return 404;
        ParkingSpot spot=search.getParkingSpot();
        return floors[0].removeVehicle(spot);
    }
    public ParkingSpot searchVehicle(String ticketId){
        return searchManager.searchVehicle( ticketId).getParkingSpot();
    }
    public int freeSpots(int floor,VehicleType vehicleType){
        int count=floors[floor].freeSpotsCount(vehicleType);
        return count;
    }
}
