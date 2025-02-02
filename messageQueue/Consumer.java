package messageQueue;

public interface Consumer {
    public void process(Message message);
}
