import java.util.PriorityQueue;

public class CallQueueManager {
    private final PriorityQueue<Call> callQueue = new PriorityQueue<>();

    public void addToQueue(Call call) {
        callQueue.add(call);
    }

    public Call getNextCall() {
        return callQueue.poll();
    }

    public void escalateCall(Call call) {
        call.priorityLevel++;
        call.status = CallStatus.ESCALATED;
        addToQueue(call);
    }
}
