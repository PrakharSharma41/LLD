package shortCode.snakeLadder;

public class Player {
    String name;
    int position;
    Player(String name){
        this.name=name;
        this.position=0;
    }
    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
