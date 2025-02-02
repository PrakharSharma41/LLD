package BattleShip;

public class Ship {
    String name;
    int size;
    public Ship(String name,int size) {
        this.name = name;
        this.size=size;
    }
    public int getSize() {
        return size;
    }
    @Override
    public String toString() {
        return "Ship [name=" + name + ", size=" + size + "]";
    }
    
}
