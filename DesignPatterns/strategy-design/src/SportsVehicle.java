import solidPrinciple.solid.src.Liskov.Vehicle;

public class SportsVehicle extends Vehicle {

    public SportsVehicle() {
        super(new SpeedDriveStrategy());
    }
    
}
