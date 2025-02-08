package pubSubMsgQueue;

import pubSubMsgQueue.model.Message;
import pubSubMsgQueue.model.TopicSubscriber;

public class TopicSubscriberImpl extends TopicSubscriber{
    private String id;
    private int sleepTimeInMillis;    
    public TopicSubscriberImpl(String id, int sleepTimeInMillis) {
        super();
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return "TopicSubscriberImpl [id=" + id + "]";
    }
    @Override
    public void consume(Message message) {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getMessage());
        try {
            Thread.sleep(sleepTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMessage());
    }    
}
