import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BranchController branchController=new BranchController();
        BookingController bookingController=new BookingController(branchController);

        // we can create setup branch instead of calling addVehicle multiple times

        branchController.addBranch("koramangala");
        branchController.addVehicle("koramangala", 1, VehicleType.SUV, 12);
        branchController.addVehicle("koramangala", 3, VehicleType.SEDAN, 10);
        branchController.addVehicle("koramangala", 3, VehicleType.BIKE, 20);


        branchController.addBranch("jayanagar");
        // branchController.addVehicle("jayanagar", 1, VehicleType.SUV, 1);
        branchController.addVehicle("jayanagar", 3, VehicleType.SEDAN, 11);
        branchController.addVehicle("jayanagar", 3, VehicleType.BIKE, 30);
        branchController.addVehicle("jayanagar", 4, VehicleType.HATCHBACK, 8);


        branchController.addBranch("malleshwaram");
        branchController.addVehicle("malleshwaram", 1, VehicleType.SUV, 11);
        branchController.addVehicle("malleshwaram", 10, VehicleType.BIKE, 3);
        branchController.addVehicle("malleshwaram", 3, VehicleType.SEDAN, 10);

        branchController.addVehicle("koramangala", 1, VehicleType.SEDAN, 10);


        LocalDateTime from = LocalDateTime.of(2025, 2, 20, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 2, 20, 12, 0);

        BookingSlot slot=new BookingSlot(from, to);
        System.out.println(bookingController.rentVehicle(VehicleType.SUV, slot)); // malleshwaram (Rs.11/hr)
        System.out.println(bookingController.rentVehicle(VehicleType.SUV, slot)); // koramangala (Rs.12/hr)
        System.out.println(bookingController.rentVehicle(VehicleType.SUV, slot)); // should fail        
    }
}
