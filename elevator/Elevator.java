package elevator;

public class Elevator {
    Direction direction;
    int currentFloor;
    public Elevator(Direction direction, int currentFloor) {
        this.direction = direction;
        this.currentFloor = currentFloor;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
}
