public class Request {
    Location location;
    int sourceFloor;
    public Direction getDirection() {
        return direction;
    }
    int destFloor;
    Direction direction;
    public Request(int sourceFloor, int destFloor, Direction direction,Location location) {
        this.sourceFloor = sourceFloor;
        this.destFloor = destFloor;
        this.location = location;
        this.direction = direction;
    }
    @Override
    public String toString() {
        return "Request [location=" + location + ", sourceFloor=" + sourceFloor + ", destFloor=" + destFloor + "]";
    }
    public Location getLocation() {
        return location;
    }
    public int getSourceFloor() {
        return sourceFloor;
    }
    public int getDestFloor() {
        return destFloor;
    }
    
}
