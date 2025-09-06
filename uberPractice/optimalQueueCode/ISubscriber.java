
public interface ISubscriber {
    // Defines behavior — what to do when a message arrives
    String getId();
    void consume(Message message) throws InterruptedException;
}
