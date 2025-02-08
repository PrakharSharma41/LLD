package pubSubMsgQueue;

public interface ISubscriber {
    int getId();
    void consume(String message);
}
