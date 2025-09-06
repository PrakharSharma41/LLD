import java.util.ArrayList;
import java.util.List;

public class Topic {
    String id;
    List<Message>messages;
    List<TopicSubscriber>topicSubscribers;
    Topic(String id){
        this.id=id;
        messages=new ArrayList<>();
        topicSubscribers=new ArrayList<>();        
    }
    void addSubscriber(TopicSubscriber subscriber){
        topicSubscribers.add(subscriber);
    }
    void addMessage(Message message){
        messages.add(message);
    }
}
