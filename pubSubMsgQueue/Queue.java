package pubSubMsgQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import pubSubMsgQueue.handler.TopicHandler;
import pubSubMsgQueue.model.Message;
import pubSubMsgQueue.model.Topic;
import pubSubMsgQueue.model.TopicSubscriber;

public class Queue {
    private final Map<String,TopicHandler>topicProcesssors;
    public Queue() {
        this.topicProcesssors=new HashMap<>();
    }

    public Topic createTopic(String topicName){
        Topic topic=new Topic(UUID.randomUUID().toString(),topicName);
        topicProcesssors.put(topic.getTopicId(), new TopicHandler(topic));
        return topic;
    }

    public void subscribe(TopicSubscriber topicSubscriber,Topic topic){
        topic.addSubscriber(topicSubscriber);
    }

    public void publish(Topic topic, Message message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicProcesssors.get(topic.getTopicId()).publish()).start();
    }
    public void resetOffset(Topic topic,TopicSubscriber topicSubscriber,int newOffset){
        for(TopicSubscriber topicSubscribesr:topic.getSubscribers()){
            if(topicSubscribesr.equals(topicSubscriber)){
                System.out.println("here running for "+topicSubscriber);
                topicSubscriber.getOffset().set(newOffset);
                System.out.println("offset1 before call is "+topicSubscriber.getOffset());
                new Thread(()->topicProcesssors.get(topic.getTopicId()).startSubsriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
