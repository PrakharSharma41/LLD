package pubSubMsgQueue.handler;

import java.util.HashMap;
import java.util.Map;

import pubSubMsgQueue.model.Topic;
import pubSubMsgQueue.model.TopicSubscriber;

public class TopicHandler {
    public Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;
    public TopicHandler(Topic topic) {
        this.topic = topic;
        subscriberWorkers=new HashMap<>();
    }

    public void publish() {
        for (TopicSubscriber topicSubscriber:topic.getSubscribers()) {
            startSubsriberWorker(topicSubscriber);
        }
    }
    public void startSubsriberWorker(TopicSubscriber topicSubscriber) {
        String subscriberId = topicSubscriber.getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
            return ;
        }
        System.out.println("topicSubscriber is "+topicSubscriber);
        SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }    
}
