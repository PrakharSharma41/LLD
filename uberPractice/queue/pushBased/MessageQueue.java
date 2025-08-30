import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    String id;
    BlockingQueue<Message> queue;
    MessageQueue(String id){
        this.id=id;
        queue=new LinkedBlockingQueue<>();
    }
    void publish(Message message){
        queue.add(message);
    }
    Message consume(){
        return queue.poll();
    }
}
