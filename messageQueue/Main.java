package messageQueue;

public class Main {
    public static void main(String[] args) {
        Controller controller=new Controller();
        controller.registerConsumer();
        controller.createMessage("aasda","payload1");
    }
}
