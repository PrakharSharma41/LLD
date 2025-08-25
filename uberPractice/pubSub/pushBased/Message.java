package pushBased;

public class Message {
    private final String payload;
    private final Long timestamp;

    public Message(String payload) {
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Message{" + "payload='" + payload + '\'' + '}';
    }
}