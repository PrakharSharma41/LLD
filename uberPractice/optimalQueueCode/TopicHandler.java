import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TopicHandler {
    Topic topic;
    Map<String, SubscriberWorker> subscriberWorkers;
    TopicHandler(Topic topic){
        this.topic=topic;
        subscriberWorkers=new HashMap<>();
    }
    void publish(){
        for (TopicSubscriber topicSubscriber:topic.topicSubscribers) {
            startSubsriberWorker(topicSubscriber);
        }        
    }
    public void startSubsriberWorker(TopicSubscriber topicSubscriber) {
        String subscriberId = topicSubscriber.getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
