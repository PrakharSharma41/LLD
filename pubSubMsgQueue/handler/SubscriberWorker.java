package pubSubMsgQueue.handler;

import pubSubMsgQueue.model.Message;
import pubSubMsgQueue.model.Topic;
import pubSubMsgQueue.model.TopicSubscriber;

public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker( Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                int curOffset = topicSubscriber.getOffset().get();
                while (curOffset >= topic.getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                        curOffset=topicSubscriber.getOffset().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = topic.getMessages().get(curOffset);
                topicSubscriber.consume(message);
                topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
