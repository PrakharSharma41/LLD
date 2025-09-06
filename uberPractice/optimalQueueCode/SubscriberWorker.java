
public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final TopicSubscriberImpl topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = (TopicSubscriberImpl)topicSubscriber;
    }
    public void run() {
        try{
            synchronized (topicSubscriber) {
                do {
                    int curOffset = topicSubscriber.offset.get();
                    while (curOffset >= topic.messages.size()) {
                        topicSubscriber.wait();
                    }
                    Message message = topic.messages.get(curOffset);
                    topicSubscriber.consume(message);
    
                    // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                    // consuming we need to increase only if it was previous one.
                    topicSubscriber.offset.compareAndSet(curOffset, curOffset + 1);
                } while (true);
            }
        }catch(Exception e){
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
