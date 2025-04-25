import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BranchService {
    private Map<String,Branch>nameToBranch;
    VehicleController vehicleController;
    public BranchService(VehicleController vehicleController){
        nameToBranch=new HashMap<>();
        this.vehicleController=vehicleController;
    }
    public void addBranch(String branchName){
        nameToBranch.computeIfAbsent(branchName, b->new Branch(branchName));
    }
    public Branch getBranch(String branchName){
        return nameToBranch.get(branchName);
    }
    public void addVehicle(String branchName, int vehicleCount, VehicleType vehicleType, int price) {
        Branch branch=nameToBranch.get(branchName);
        if(branch==null){
            System.out.println(branchName+" branch is not created");
            return;
        }
        for(int i=0;i<vehicleCount;i++){
            Vehicle vehicle=vehicleController.createVehicle( vehicleType, price);
            branch.addVehicle(vehicle);
        }
    }    
    public List<Vehicle>getVehicleBasedOnType(String branchName,VehicleType vehicleType){
        return nameToBranch.get(branchName).getVehicleBasedOnType(vehicleType);
    }
    public List<Branch>getAllBranch(){
         return new ArrayList<>(nameToBranch.values());
    }


}
