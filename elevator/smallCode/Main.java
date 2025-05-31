public class Main {
    public static void main(String[] args) {

        // use below code if only one elevator is required
        // dont use elevator controller in this case
        // ElevatorHandler handler=new ElevatorHandler(0,0);
        // Request upRequest1 = new Request(1, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        // Request upRequest2 = new Request(2, 3, Direction.UP, Location.OUTSIDE_ELEVATOR);
        // Request upRequest3 = new Request(handler.getCurrentFloor(), 6, Direction.UP, Location.OUTSIDE_ELEVATOR);

        // Request downRequest1 = new Request(3, 1, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        // Request downRequest2 = new Request(4, 2, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        // Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);

        // handler.sendRequest(upRequest1);
        // handler.sendRequest(upRequest2);
        // handler.sendRequest(upRequest3);
        // handler.sendRequest(downRequest1);
        // handler.sendRequest(downRequest2);
        // handler.sendRequest(downRequest3);
        // handler.processRequests();    
        
        //********************************* */

        // use below only when multiple elevator present
        ElevatorController elevatorController=new ElevatorController(3);
        Request upRequest1 = new Request(1, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request upRequest2 = new Request(2, 3, Direction.UP, Location.OUTSIDE_ELEVATOR);

        Request downRequest1 = new Request(3, 1, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request downRequest2 = new Request(4, 2, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        elevatorController.sendRequest(upRequest1);
        elevatorController.sendRequest(upRequest2);
        elevatorController.sendRequest(downRequest1);
        elevatorController.sendRequest(downRequest2);
        elevatorController.sendRequest(downRequest3);
        elevatorController.run();
    }
}
// 1 up1
// 2 up2
// 3 do1 do2 do3