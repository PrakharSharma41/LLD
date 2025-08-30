import java.util.concurrent.ConcurrentHashMap;

public class QueueManager {
    ConcurrentHashMap<String,MessageQueue>manager;
    QueueManager(){
        manager=new ConcurrentHashMap<>();
    }
    MessageQueue createQueueIfAbsent(String name){
        return manager.computeIfAbsent(name, k->new MessageQueue(name));
    }
    MessageQueue getMessageQueue(String name){
        return manager.get(name);
    }
}
