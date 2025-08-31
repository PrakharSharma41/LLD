package pullBased;
import java.util.Queue;

public class Producer{
    String id;
    String queueName;
    QueueManager manager;
    public Producer(String id, String queueName, QueueManager manager) {
        this.id = id;
        this.queueName = queueName;
        this.manager = manager;
    }
    void publish(Message message){
        MessageQueue queue=manager.getMessageQueue(queueName);
        if(queue!=null){
            queue.publish(message);
        }else{
            System.out.println(queueName+" is not present");
        }
    }
    
}
