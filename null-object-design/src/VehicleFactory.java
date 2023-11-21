public class VehicleFactory {
    public static Vehicle getVehicle(String name){
        if("car".equals(name)){
            return new Car();
        }        
        return new NullObjectClass();
    }
}
