package messageQueue;

public class Message {
    String regex;
    String payload;
    public Message(String message, String payload) {
        this.regex = message;
        this.payload = payload;
    }
    public String getRegex() {
        return regex;
    }
    public String getPayload() {
        return payload;
    }
    
}
