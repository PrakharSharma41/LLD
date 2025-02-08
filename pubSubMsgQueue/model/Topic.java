package pubSubMsgQueue.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    String topicId;
    String topicName;
    public List<Message> getMessages() {
        return messages;
    }
    List<TopicSubscriber>subscribers;
    List<Message>messages;
    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        subscribers=new ArrayList<>();
        messages=new ArrayList<>();
    }
    public void addSubscriber(TopicSubscriber subcriber){
        subscribers.add(subcriber);
    }
    public synchronized void addMessage( Message message) {
        messages.add(message);
    }
    public String getTopicName() {
        return topicName;
    }
    public String getTopicId() {
        return topicId;
    }
    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }
}
