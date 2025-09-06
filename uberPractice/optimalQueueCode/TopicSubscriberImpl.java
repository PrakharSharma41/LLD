import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriberImpl implements TopicSubscriber{
    String id;
    AtomicInteger offset;
    public TopicSubscriberImpl(String id) {
        this.id = id;
        offset=new AtomicInteger(0);
    }
    @Override
    public void consume(Message message) {
        System.out.println(message+" consumed");
        try{
            Thread.sleep(1000);
        }catch(Exception e){

        }
    }
    @Override
    public String getId() {
        return id;
    }
    
}
