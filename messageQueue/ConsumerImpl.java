package messageQueue;

import java.util.List;

class ConsumerImpl implements Consumer{
    String name;
    String regex;
    List<Consumer>predessors;
    ConsumerProcessStrategy strategy;
    public String getRegex() {
        return regex;
    }
    public String getName() {
        return name;
    }
    public ConsumerImpl(String name,String regex, List<Consumer> predeccors,ConsumerProcessStrategy strategy) {
        this.name = name;
        this.regex=regex;
        this.predessors = predeccors;
        this.strategy=strategy;
    }

    @Override
    public void process(Message message) {
        this.strategy.process(message);
        System.out.println("message processed by "+name+" message payload is "+message.payload);
    }
}