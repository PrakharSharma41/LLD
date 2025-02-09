package PullBasedQueue;

public class Message {
    String content;
    Long ttl;
    Long timeStamp;
    public Message(String content, Long timeStamp,Long ttl) {
        this.content = content;
        this.ttl = ttl;
        this.timeStamp=timeStamp;
    }
    @Override
    public String toString() {
        return "Message [content=" + content + ", ttl=" + ttl + ", timeStamp=" + timeStamp + "]";
    }
    public Long getTimeStamp() {
        return timeStamp;
    }
    public String getContent() {
        return content;
    }
    public Long getTtl() {
        return ttl;
    }
    
}
