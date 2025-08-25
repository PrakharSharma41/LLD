package pullBased;

public class Message {
    private final String data;
    private final long timestamp;

    public Message(String data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public String getData() { return data; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Message{" + "data='" + data + '\'' + ", ts=" + timestamp + '}';
    }    
}
