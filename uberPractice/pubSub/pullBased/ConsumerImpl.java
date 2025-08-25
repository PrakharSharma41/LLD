package pullBased;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerImpl implements Consumer{
    Map<Topic,Integer>topicOffset;
    String consumerId;
    ConsumerImpl(String consumerId){
        this.consumerId=consumerId;
        topicOffset=new ConcurrentHashMap<>();
        
    }
    public String getId() {
        return consumerId;
    }
    @Override
    public void subscribe(Topic topic) {
        topicOffset.put(topic, 0);
        topic.addConsumer(this);
    }

    @Override
    public String toString() {
        return "ConsumerImpl [consumerId=" + consumerId + "]";
    }
    @Override
    public void pull(Topic topic) {
        Integer offset=topicOffset.get(topic);
        System.out.println(offset);
        if(offset==null)return;
        Message message=topic.readFrom(offset);
        System.out.println(message);
        if(message!=null){
            System.out.println(this+" is reading "+message);
            topicOffset.put(topic,offset+1);
        }
    }
    
}
