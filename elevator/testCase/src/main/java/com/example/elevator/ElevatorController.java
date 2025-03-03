package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    List<ElevatorHandler>elevatorHandlers;

    public ElevatorController(int num) {
        this.elevatorHandlers=new ArrayList<>();
        for(int i=0;i<num;i++){
            elevatorHandlers.add(new ElevatorHandler(i,i));
        }
    }
    public Request requestHandler(Request request){
        ElevatorHandler elevatorHandler=findOptimalElevatorHandler(request);
        if(request.direction==Direction.UP)elevatorHandler.sendUpRequest(request);
        else elevatorHandler.sendDownRequest(request);
        return request;
    }
    public ElevatorHandler findOptimalElevatorHandler(Request request) {
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
    public void run(){
        for (ElevatorHandler elevatorHandler : elevatorHandlers) {
            elevatorHandler.run();
        }
    }   
}
