package flyweightDesign;

public class Robot{
    int X;
    int Y;
    String type;
    Sprites body; // heavy object
    public Robot(int x, int y, String type, Sprites body) {
        X = x;
        Y = y;
        this.type = type;
        this.body = body;
    }
    // getters and setters
}
// sprites is an heavy object, when we want to create multiple robot objects
// multiple sprites object also need to be created
