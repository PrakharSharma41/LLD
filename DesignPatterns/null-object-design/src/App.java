public class App {
    public static void main(String[] args) throws Exception {
        Vehicle vehicle =VehicleFactory.getVehicle("car");
        System.out.println(vehicle.getTankCapacity());
    }
}
