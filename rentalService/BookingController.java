public class BookingController {
    BookingService bookingService;
    public BookingController(BranchController branchController){
        bookingService=new BookingService(branchController);
    }   
    public String rentVehicle(VehicleType vehicleType,BookingSlot bookingSlot){
        return bookingService.createBooking(vehicleType, bookingSlot);
    }
}
