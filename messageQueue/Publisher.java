package messageQueue;

public interface Publisher {
    void addToMessageQueue(Message message,MessageQueue queue);
}
