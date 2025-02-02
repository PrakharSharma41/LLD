package messageQueue;

import java.util.HashMap;

public class ConsumerController {
    HashMap<String,Consumer>consumers=new HashMap<>();
    public void addConsumer(ConsumerImpl consumer){
        consumers.put(consumer.getName(), consumer);
    }
    public Consumer getConsumer(String name){
        return consumers.get(name);
    }
    public void removeConsumer(String name){
        consumers.remove(name);
    }
}
