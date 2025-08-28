import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

class QueueManager {
    private final Map<String, BlockingQueue<Message>> queues = new ConcurrentHashMap<>();

    public BlockingQueue<Message> getQueue(String queueName) {
        return queues.computeIfAbsent(queueName, k -> new LinkedBlockingQueue<>());
    }
}
