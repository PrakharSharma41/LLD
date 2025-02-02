package messageQueue;

public class Controller {
    MessageQueue messageQueue;
    Publisher publisher;
    ConsumerController consumerController;
    Controller(){
        messageQueue=new MessageQueue();
        publisher=new PublisherImpl();
    }
    public void registerConsumer(){
        ConsumerImpl consumer1=new ConsumerImpl("one","ba", null,new ConsumerProcessStrategy());
        ConsumerImpl consumer2=new ConsumerImpl("two","ba", null,new ConsumerProcessStrategy());
        messageQueue.registerConsumer(consumer1,consumer1.getRegex());
        messageQueue.registerConsumer(consumer2,consumer2.getRegex());

    }
    public void createMessage(String regex,String payload){
        Message message=new Message(regex, payload);
        messageQueue.setMessage(message);
    }
}
