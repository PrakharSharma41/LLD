package pubSubMsgQueue.model;

import java.util.concurrent.atomic.AtomicInteger;


public abstract class TopicSubscriber {
    AtomicInteger offset;
    public TopicSubscriber() {
        this.offset = new AtomicInteger(0);
    }
    public abstract String getId();
    public abstract void consume(Message message);
    public AtomicInteger getOffset() {
        return offset;
    }
}
