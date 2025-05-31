import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    List<ElevatorHandler>elevatorHandlers;
    ElevatorController(int num){
        elevatorHandlers=new ArrayList<>();
        for(int i=1;i<=num;i++){
            elevatorHandlers.add(new ElevatorHandler(i, i));
        }
    }
    public void sendRequest(Request request){
        ElevatorHandler handler=findOptimalElevatorHandler(request);
        if(handler!=null){
            handler.sendRequest(request);
        }
    }
    public void run(){
        for(ElevatorHandler handler:elevatorHandlers){
            handler.processRequests();
        }
    }
    private ElevatorHandler findOptimalElevatorHandler(Request request) {
        int sourceFloor=request.sourceFloor;
        int destinationFloor=request.destFloor;
        ElevatorHandler optimalElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorHandler elevatorHandler : elevatorHandlers) {
            int distance = Math.abs(sourceFloor - elevatorHandler.elevator.getCurrentFloor());
            if (distance < minDistance) {
                minDistance = distance;
                optimalElevator = elevatorHandler;
            }
        }
        return optimalElevator;
    }     

}
