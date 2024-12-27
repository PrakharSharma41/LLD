package commandDesign;

public class Main {
    public static void main(String[] args) {
        AirConditioner airConditioner=new AirConditioner();
        MyRemoteControl remoteObj=new MyRemoteControl();
        remoteObj.setCommand(new TurnACOnCommand(airConditioner));
        remoteObj.pressButton();
    }
}
