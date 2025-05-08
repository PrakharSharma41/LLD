package DesignPatterns.flyweightDesign;

public class Main {
    public static void main(String[] args) {
        IRobot humanoidRobot1=RoboticFactoy.createRobot("HUMANOID");
        humanoidRobot1.display(1, 2);
        IRobot humanoidIRobot2=RoboticFactoy.createRobot("HUMANOID");// returns previously created object
        humanoidIRobot2.display(2, 3);
    }
}
