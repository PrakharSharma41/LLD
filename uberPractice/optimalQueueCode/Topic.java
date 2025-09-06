import java.util.ArrayList;
import java.util.List;

public class Topic {
    String name;
    public Topic(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic [name=" + name + "]";
    }

    private final List<Message> messages = new ArrayList<>();
    private final List<TopicSubscriber> subscribers = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void publish() {
        for (TopicSubscriber topicSubscriber : subscribers) {
            topicSubscriber.startConsuming(this);
        }
    }

}
