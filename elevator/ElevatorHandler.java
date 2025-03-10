package elevator;

import java.util.PriorityQueue;

public class ElevatorHandler {
    int id;
    Elevator elevator=null;
    PriorityQueue<Request> upQueue;
    PriorityQueue<Request> downQueue;
    public ElevatorHandler(int id,int floor) {
        this.id=id;
        createElevator(floor);
        upQueue = new PriorityQueue<>((a, b) -> a.destFloor - b.destFloor);
        downQueue =  new PriorityQueue<>((a, b) -> b.destFloor - a.destFloor);        
    }
    public Elevator createElevator(int floor){
        this.elevator=new Elevator(Direction.IDLE, floor);
        return elevator;
    }
    public void sendUpRequest(Request upRequest) {
        if (upRequest.location == Location.OUTSIDE_ELEVATOR) {
            upQueue.offer(new Request(upRequest.sourceFloor,
            upRequest.sourceFloor,
                Direction.UP,
                Location.OUTSIDE_ELEVATOR));
        }        
        upQueue.offer(upRequest);
    }

    public void sendDownRequest(Request downRequest) {
        if (downRequest.location == Location.OUTSIDE_ELEVATOR) {
            downQueue.offer(new Request(downRequest.sourceFloor,
                downRequest.sourceFloor,
                Direction.DOWN,
                Location.OUTSIDE_ELEVATOR));
        }        
        downQueue.offer(downRequest);
    }    
    public void run() {
        if(upQueue.isEmpty()&&downQueue.isEmpty()){
            return;
        }
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            processRequests();
        }
        System.out.println("Finished all requests.");
        elevator.setDirection(Direction.IDLE);
    }

    private void processRequests() {
        Direction dir=elevator.getDirection();
        if (dir == Direction.UP || dir == Direction.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processUpRequest() {
        while (!upQueue.isEmpty()) {
            Request upRequest = upQueue.poll();
            // Communicate with hardware
            int currentFloor = upRequest.destFloor;
            System.out.println("Processing up requests. Elevator "+id+"stopped at floor " + currentFloor + ".");
            elevator.setCurrentFloor(currentFloor);
        }
        if (!downQueue.isEmpty()) {
            elevator.setDirection(Direction.DOWN);
        } else {
            elevator.setDirection(Direction.IDLE);
        }
    }

    private void processDownRequest() {
        while (!downQueue.isEmpty()) {
            Request downRequest = downQueue.poll();
            // Communicate with hardware
            int currentFloor = downRequest.destFloor;
            System.out.println("Processing down requests. Elevator "+id+"stopped at floor " + currentFloor + ".");
            elevator.setCurrentFloor(currentFloor);
        }
        if (!upQueue.isEmpty()) {
            elevator.setDirection(Direction.UP);
        } else {
            elevator.setDirection(Direction.IDLE);
        }
    }

}
