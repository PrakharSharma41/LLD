package messageQueue;

public class ConsumerProcessStrategy implements Consumer{

    @Override
    public void process(Message message) {
        System.out.println("some handling for payload"+message);
    }
    
}
