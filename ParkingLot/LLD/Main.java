package ParkingLot.LLD;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot("parkingLot1");
        parkingLot.createParkingSpots(0, "1", true, VehicleType.BIKE);
        parkingLot.createParkingSpots(0, "2", true, VehicleType.BIKE);
        parkingLot.createParkingSpots(0, "3", true, VehicleType.BIKE);
        // parkingLot.createParkingSpots(0, "4", true, VehicleType.BIKE);


        int count=parkingLot.freeSpots(0, VehicleType.BIKE);
        // System.out.println(count+" free slots ");
        Vehicle vehicle1=new Vehicle( VehicleType.BIKE,"one");
        Vehicle vehicle2=new Vehicle( VehicleType.BIKE,"two");
        
        Runnable runnable1=()-> {            
            // for(int i=0;i<5;i++){
                Vehicle vehicle=new Vehicle(VehicleType.BIKE, Thread.currentThread().getName());
                parkingLot.park(vehicle);
            // }
            // ParkingResult parkingResult=parkingLot.park(vehicle1);
        };
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(int i=0;i<4;i++){
            executor.submit(runnable1);
        }
        // Thread thread=new Thread(runnable1);
        // thread.start();
        // ParkingResult parkingResult2=parkingLot.park(vehicle2);

        // String ticket=parkingResult1.getTicketId();

        // System.out.println(parkingResult1);
        // parkingLot.park(vehicle2);
        // System.out.println(parkingLot.searchVehicle(ticket));
        // parkingLot.removeVehicle(ticket);
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count=parkingLot.freeSpots(0, VehicleType.BIKE);


        System.out.println(count+" jpfree slots at last");
    }
}
