package producerConsumer;

public interface Resource {    
    public void consume();
    public void produce(int itemId);
}
