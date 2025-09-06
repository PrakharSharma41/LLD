import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private  Map<String, Topic> topicProcessors;

    public Queue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        Topic topic = new Topic(topicName);
        topicProcessors.put(topic.name, topic);
        System.out.println("Created topic: " + topic.name);
        return topic;
    }

    public void subscribe(TopicSubscriber subscriber, Topic topic) {
        topic.addSubscriber(subscriber);
        System.out.println(subscriber.subscriber.getId() + " subscribed to topic: " + topic.name);
    }

    public void publish(Topic topic,  Message message) {
        topic.addMessage(message);
        System.out.println(message.msg + " published to topic: " + topic.name);
        new Thread(() -> topicProcessors.get(topic.name).publish()).start();
    }

    // public void resetOffset( Topic topic,  TopicSubscriber subscriber,  Integer newOffset) {
    //     for (TopicSubscriber topicSubscriber : topic.topicSubscribers) {
    //         if (topicSubscriber.equals(subscriber)) {
    //             topicSubscriber.offset.set(newOffset);
    //             System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
    //             new Thread(() -> topicProcessors.get(topic.getTopicId()).startSubsriberWorker(topicSubscriber)).start();
    //             break;
    //         }
    //     }
    // }    
}
