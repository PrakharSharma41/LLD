package pullBased;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PubSubService {
    private final Map<String, Topic> topicRegistry;
    PubSubService(){
        topicRegistry=new ConcurrentHashMap<>();
    }
    public void createTopic(String topicName) {
        topicRegistry.putIfAbsent(topicName, new Topic(topicName));
        System.out.println("Topic " + topicName + " created");
    }
    public void subscribe(String topicName, Consumer consumer) {
        Topic topic = topicRegistry.get(topicName);
        if (topic == null)
            throw new IllegalArgumentException("Topic not found: " + topicName);
        topic.addConsumer(consumer);
        consumer.subscribe(topic);
        System.out.println("Subscriber '" + consumer.getId() + "' subscribed to topic: " + topicName);
    }
    public void unsubscribe(String topicName, Consumer subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if (topic != null){
            topic.removeConsumer(subscriber);            
        }            
        System.out.println("Subscriber '" + subscriber.getId() + "' unsubscribed from topic: " + topicName);
    }
    public void publish(String topicName, Message message) {
        System.out.println("Publishing message to topic: " + topicName);
        Topic topic = topicRegistry.get(topicName);
        if (topic == null) throw new IllegalArgumentException("Topic not found: " + topicName);
        topic.addMessage(message);
    }    
    public void pull(Consumer consumer, String topicName) {
        Topic topic=topicRegistry.get(topicName);
        if(topic==null)return;
        consumer.pull(topic);
    }
}
