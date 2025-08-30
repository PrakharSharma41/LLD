import java.util.concurrent.BlockingQueue;

public class Consumer {
    String id;
    String queueName;
    QueueManager manager;
    public Consumer(String id, String queueName, QueueManager manager) {
        this.id = id;
        this.queueName = queueName;
        this.manager = manager;
    }
    void consume(){
        MessageQueue queue=manager.getMessageQueue(queueName);
        if(queue!=null){
            BlockingQueue<Message> q=queue.queue;
            Message msg;
            try {
                msg = q.take();
                System.out.println(msg+" consumed by "+this.id);
            } catch (InterruptedException e) {
            }
        }else{
            System.out.println(queueName+" is not present");
        }

    }
}
