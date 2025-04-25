
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    String branchName;
    Map<VehicleType,List<Vehicle>>typeToVehicle;
    public Branch(String branchName) {
        this.branchName = branchName;
        typeToVehicle=new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        typeToVehicle.computeIfAbsent(vehicle.getVehicleType(), k->new ArrayList<>()).add(vehicle);
    }
    public List<Vehicle>getVehicleBasedOnType(VehicleType type){
        return typeToVehicle.getOrDefault(type, new ArrayList<>());
    }
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
