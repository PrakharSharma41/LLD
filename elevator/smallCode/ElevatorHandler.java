import java.util.PriorityQueue;

public class ElevatorHandler {
    Elevator elevator;
    PriorityQueue<Request>upQueue;
    PriorityQueue<Request>downQueue;
    public ElevatorHandler(int id,int floor) {
        createElevator(id,floor);
        upQueue=new PriorityQueue<>((Request a,Request b)->{
            return a.destFloor-b.destFloor;
        });
        downQueue=new PriorityQueue<>((Request a,Request b)->{
            return b.destFloor-a.destFloor;
        });
    }   
    private void createElevator(int id,int floor){
        this.elevator=new Elevator(id,floor, Direction.IDLE);
    }
    public void sendRequest(Request request){
        if(request.getDirection()==Direction.UP){
            if(request.location==Location.OUTSIDE_ELEVATOR){
                upQueue.add(new Request(request.getSourceFloor(), request.getSourceFloor(), request.getDirection(), request.getLocation()));
            }
            upQueue.add(request);
        }else if(request.getDirection()==Direction.DOWN){
            if(request.location==Location.OUTSIDE_ELEVATOR){
                downQueue.add(new Request(request.getSourceFloor(), request.getSourceFloor(), request.getDirection(), request.getLocation()));
            }
            downQueue.add(request);
        }
    }

    public void processRequests(){
        if(elevator.direction==Direction.UP||elevator.direction==Direction.IDLE){
            processUpRequests();
            processDownRequests();
        }else{
            processDownRequests();
            processUpRequests();
        }
    }
    private void processUpRequests(){
        while(!upQueue.isEmpty()){
            Request request=upQueue.poll();
            elevator.setCurrentFloor(request.getDestFloor());
            System.out.println("elevator"+elevator+"stopped at "+elevator.getCurrentFloor());
        }
        if(!downQueue.isEmpty()){
            elevator.setDirection(Direction.DOWN);
        }else{
            elevator.setDirection(Direction.IDLE);
        }
    }
    private void processDownRequests(){
        while(!downQueue.isEmpty()){
            Request request=downQueue.poll();
            elevator.setCurrentFloor(request.getDestFloor());
            System.out.println("elevator"+elevator+" stopped at "+elevator.getCurrentFloor());
        }
        if(!upQueue.isEmpty()){
            elevator.setDirection(Direction.UP);
        }else{
            elevator.setDirection(Direction.IDLE);
        }
    }
    public int getCurrentFloor(){
        return elevator.getCurrentFloor();
    }
}
