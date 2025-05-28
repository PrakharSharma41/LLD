package singleFloor;

import singleFloor.entities.ParkingFloor;
import singleFloor.entities.ParkingSpot;
import singleFloor.entities.Vehicle;
import singleFloor.entities.VehicleType;

public class ParkingLot {
    ParkingFloor parkingFloors;
    ParkingSpotSearchStrategy searchStrategy;
    TicketSearchManager ticketSearchManager;
    public ParkingLot(int floors) {
        parkingFloors=new ParkingFloor(0);
        ticketSearchManager=new TicketSearchManager();
        setSearchStrategy(parkingFloors);
    }
    public void setSearchStrategy(ParkingFloor parkingFloors){
        searchStrategy=new ParkingSpotSearchStrategyImpl(parkingFloors);
    }

    public void addParkingSpot(int spotId,VehicleType vehicleType){
        parkingFloors.addParkingSpot(spotId, vehicleType);
    }
    public int parkVehicle(Vehicle vehicle){
        if(searchStrategy==null){
            return -1;
        }
        ParkingSpot spot=searchStrategy.findParkingSpot(vehicle.getVehicleType());
        if(spot!=null){
            int ticketId=parkingFloors.park(vehicle, spot);
            ticketSearchManager.putTicket(ticketId, spot);
            return ticketId;
        }else{
            System.out.println("spot cannot be found");
            return -1;
        }
    }
    public void removeVehicle(int ticketId){
        ParkingSpot spot=ticketSearchManager.getParkingSpotFromTicket(ticketId);
        if(spot!=null){
            parkingFloors.removeVehicle(spot);
        }else{
            System.out.println("invalid ticket");
        }
    }
    
}
