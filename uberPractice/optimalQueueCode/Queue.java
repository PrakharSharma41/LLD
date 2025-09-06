import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private  Map<String, TopicHandler> topicProcessors;

    public Queue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        final Topic topic = new Topic(topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.id, topicHandler);
        System.out.println("Created topic: " + topic.id);
        return topic;
    }

    public void subscribe(TopicSubscriber subscriber, Topic topic) {
        topic.addSubscriber(subscriber);
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.id);
    }

    public void publish( Topic topic,  Message message) {
        topic.addMessage(message);
        System.out.println(message.msg + " published to topic: " + topic.id);
        new Thread(() -> topicProcessors.get(topic.id).publish()).start();
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
