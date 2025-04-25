import java.util.ArrayList;
import java.util.List;

public class BookingService {
    BookingStrategy bookingStrategy;
    BranchController branchController;
    public BookingService(BranchController branchController){
        bookingStrategy=new LowestPriceStrategy();
        this.branchController=branchController;
    }
    public String createBooking(VehicleType vehicleType,BookingSlot bookingSlot){
        List<Branch>branches=branchController.getAllBranch();
        Branch selectedBranch=null;
        Vehicle bookedVehicle=null;
        for(Branch branch:branches){
            List<Vehicle>vehicles=branchController.getVehicleBasedOnType(branch.getBranchName(), vehicleType);
            if(vehicles!=null&&vehicles.size()>0){
                Vehicle selectedVehicle=bookingStrategy.selectVehicle(vehicles, bookingSlot);
                if(selectedVehicle==null)continue;
                if(bookedVehicle==null||bookedVehicle.getPrice()>selectedVehicle.getPrice()){
                    bookedVehicle=selectedVehicle;
                    selectedBranch=branch;
                }
            }
        }
        if(bookedVehicle==null){
            return "booking failed";
        }
        branchController.bookVehicle(bookedVehicle,bookingSlot);
        Booking booking=new Booking((int)(Math.random()*90), bookingSlot, bookedVehicle);
        return booking.toString();
    }
}
