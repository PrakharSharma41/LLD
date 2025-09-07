public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot(2);
        parkingLot.addParkingSpot(0, 0, VehicleType.BIKE);
        parkingLot.addParkingSpot(1, 0, VehicleType.CAR);
        Vehicle bike=new Vehicle("123", VehicleType.BIKE);
        Vehicle car=new Vehicle("213", VehicleType.CAR);

        String ticketId1=parkingLot.parkVehicle(car);

        String ticketId2=parkingLot.parkVehicle(bike);
        parkingLot.unParkVehicle(ticketId2);

        Vehicle bike1=new Vehicle("123", VehicleType.BIKE);
        parkingLot.parkVehicle(bike1);

        // parkingLot.parkVehicle(bike1);
    }
}
