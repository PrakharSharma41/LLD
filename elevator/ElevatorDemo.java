package elevator;

public class ElevatorDemo {
    public static void main(String[] args) {
        // use below code if only one elevator is required
        // dont use elevator controller in this case
        
        // ElevatorHandler elevatorHandler=new ElevatorHandler(0,0);
        // Request upRequest1 = new Request(elevatorHandler.elevator.getCurrentFloor(), 5, Direction.UP, Location.INSIDE_ELEVATOR);
        // Request upRequest2 = new Request(elevatorHandler.elevator.getCurrentFloor(), 3, Direction.UP, Location.INSIDE_ELEVATOR);

        // Request downRequest1 = new Request(elevatorHandler.elevator.getCurrentFloor(), 1, Direction.DOWN, Location.INSIDE_ELEVATOR);
        // Request downRequest2 = new Request(elevatorHandler.elevator.getCurrentFloor(), 2, Direction.DOWN, Location.INSIDE_ELEVATOR);
        // Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);

        // elevatorHandler.sendUpRequest(upRequest1);
        // elevatorHandler.sendUpRequest(upRequest2);

        // // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        // elevatorHandler.sendDownRequest(downRequest3);

        // // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        // elevatorHandler.sendDownRequest(downRequest1);
        // elevatorHandler.sendDownRequest(downRequest2);


        // elevatorHandler.run();       
        
        ElevatorController elevatorController=new ElevatorController(3);
        Request upRequest1 = new Request(1, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request upRequest2 = new Request(2, 3, Direction.UP, Location.OUTSIDE_ELEVATOR);

        Request downRequest1 = new Request(3, 1, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request downRequest2 = new Request(4, 2, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        elevatorController.requestHandler(upRequest1);
        elevatorController.requestHandler(upRequest2);
        elevatorController.requestHandler(downRequest1);
        elevatorController.requestHandler(downRequest2);
        elevatorController.requestHandler(downRequest3);
        elevatorController.run();

    }
}
