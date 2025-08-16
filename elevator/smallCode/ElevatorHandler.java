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
        if(elevator.direction==Direction.IDLE||elevator.direction==Direction.UP){
            processRequestDirection(upQueue, Direction.UP);
        }else{
            processRequestDirection(downQueue, Direction.DOWN);
        }
    }
    private void processRequestDirection(PriorityQueue<Request>queue,Direction direction){
        if(queue==null)return ;
        while (!queue.isEmpty()) {
            Request request=queue.poll();
            elevator.setCurrentFloor(request.getDestFloor());
            System.out.println("elevator stopped at "+elevator.getCurrentFloor());
        }
        if(direction==Direction.IDLE||direction==Direction.UP){
            processRequestDirection(downQueue, Direction.DOWN);
        }else{
            processRequestDirection(upQueue, Direction.UP);
        }
    }
    public int getCurrentFloor(){
        return elevator.getCurrentFloor();
    }
}
