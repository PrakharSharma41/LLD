package entities;
public class Player {
    String name;
    public int currentPosition;
    public Player(String name, int currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }
    @Override
    public String toString() {
        return "Player [name=" + name + ", currentPosition=" + currentPosition + "]";
    }
    
}
