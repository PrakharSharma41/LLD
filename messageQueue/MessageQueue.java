package messageQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MessageQueue {
    Map<String,Map<Consumer,Integer>>queue=new HashMap<>();
    Map<Message,Integer>messages=new HashMap<>();
    List<Message>errorMessagesList=new ArrayList<>();
    // private final ScheduledExecutorService errorMessages = Executors.newSingleThreadScheduledExecutor();
    
    public void registerConsumer(Consumer consumer,String regex){
        if(queue.containsKey(regex)==false)queue.put(regex,new HashMap<>());
        Map<Consumer,Integer>consumerHashMap=queue.get(regex);
        consumerHashMap.put(consumer, null);
    }
    public void removeConsumer(Consumer consumer){        
        // implement here
    }
    public MessageQueue() {
        // errorMessages.scheduleAtFixedRate(this::cleanup, 0, 1, TimeUnit.MINUTES);        
    }
    public void cleanup(){
        return ;
        // for(Message message:errorMessagesList){
        //     notifyQueue(message);
        // }
    }
    public void setMessage(Message message){
        messages.put(message, null);
        notifyQueue(message);
    }
    public void notifyQueue(Message message){
        String messagePayload = message.getPayload();
        boolean isMatched = false;
        
        for (Map.Entry<String, Map<Consumer,Integer>> entry : queue.entrySet()) {
            String regex = entry.getKey();
            Pattern pattern = Pattern.compile(regex);

            if (pattern.matcher(messagePayload).find()) {
                isMatched = true;
                Map<Consumer,Integer>mp=entry.getValue();
                for (Consumer consumer : mp.keySet()) {
                    consumer.process(message);
                }
            }
        }

        if (!isMatched) {
            System.out.println("No consumers matched for message: " + messagePayload);
        }
    }
}

