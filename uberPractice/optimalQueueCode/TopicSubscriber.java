
public interface TopicSubscriber {
    void consume(Message message);
    String getId();
}
