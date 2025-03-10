package elevator;

public class Request {
    int sourceFloor;
    int destFloor;
    Location location;
    Direction direction;
    public Request(int sourceFloor, int destFloor, Direction direction,Location location) {
        this.sourceFloor = sourceFloor;
        this.destFloor = destFloor;
        this.location = location;
        this.direction = direction;
    }
    
}
