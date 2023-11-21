
public class Vehicle {
    DriveStrategy driveStrategy;
    public void drive(){
        driveStrategy.drive();
    }
    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }
}
