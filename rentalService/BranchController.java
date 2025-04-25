

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BranchController {
    BranchService branchService;
    VehicleController vehicleController;
    public BranchController(){
        vehicleController=new VehicleController();        
        branchService=new BranchService(vehicleController);
    }
    public void addBranch(String branchName){
        branchService.addBranch(branchName);
    }
    public Branch getBranch(String branchName){
        return branchService.getBranch(branchName);
    }
    public void addVehicle(String branchName,int vehicleCount,VehicleType vehicleType, int price){
        branchService.addVehicle(branchName,vehicleCount,vehicleType,price);
    }
    public List<Vehicle>getVehicleBasedOnType(String branchName,VehicleType vehicleType){
        return branchService.getVehicleBasedOnType(branchName,vehicleType);
    }
    public List<Branch>getAllBranch(){
        return branchService.getAllBranch();
    }
    public void bookVehicle(Vehicle vehicle,BookingSlot slot){
        vehicleController.bookVehicle(vehicle, slot);;
    }    
}
