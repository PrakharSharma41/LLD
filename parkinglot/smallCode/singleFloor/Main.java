package singleFloor;

import singleFloor.entities.Vehicle;
import singleFloor.entities.VehicleType;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot(0);
        parkingLot.addParkingSpot(0, VehicleType.SEDAN);
        parkingLot.addParkingSpot(1, VehicleType.SEDAN);
        int ticket1=parkingLot.parkVehicle(new Vehicle(0, VehicleType.SEDAN));
        int ticket2=parkingLot.parkVehicle(new Vehicle(1, VehicleType.SEDAN));
        parkingLot.removeVehicle(ticket2);
        int ticket3=parkingLot.parkVehicle(new Vehicle(2, VehicleType.SEDAN));
        parkingLot.removeVehicle(ticket3);

        int ticket4=parkingLot.parkVehicle(new Vehicle(3, VehicleType.SEDAN));


        System.out.println(ticket1);
        System.out.println(ticket2);
        System.out.println(ticket3);
        System.out.println(ticket4);
    }
}

// schema:

// Vehicle table:
// vehicle_id, registration number, user_id,vehicleType(car/bike/truck/van)

// Spot table:
// spot_id, floor_id, vehicle_id,vehicleType (spot available can be determined by vehicle_id is null or not)

// Ticket table:
// ticket_id, user_id, List<spot_ids>

// User table:
// user_id, name, mobile, vehicle_id

