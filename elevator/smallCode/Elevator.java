public class Elevator{
    int id;
    int currentFloor;
    Direction direction;
    public Elevator(int id,int currentFloor, Direction direction) {
        this.id=id;
        this.currentFloor = currentFloor;
        this.direction = direction;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    @Override
    public String toString() {
        return "Elevator [id=" + id + "]";
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
}