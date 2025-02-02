package messageQueue;

public class PublisherImpl implements Publisher{
    @Override
    public void addToMessageQueue(Message message,MessageQueue queue) {
        queue.setMessage(message);
    }
    
}
