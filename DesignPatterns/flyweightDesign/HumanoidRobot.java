package DesignPatterns.flyweightDesign;

public class HumanoidRobot implements IRobot{
    private String type;
    private Sprites body;
    public HumanoidRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }
    @Override
    public void display(int x, int y) {//passing extrinsic data as method parameters
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
    public String getType() {
        return type;
    }
    public Sprites getBody() {
        return body;
    }
    // we have made this class immutable
}
