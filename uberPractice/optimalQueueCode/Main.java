public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();
        Topic topic1 = queue.createTopic("t1");
        Topic topic2 = queue.createTopic("t2");
        TopicSubscriber sub1 = new TopicSubscriber(new SleepingSubscriber("sub1", 10000));
        TopicSubscriber sub2 = new TopicSubscriber(new SleepingSubscriber("sub2", 10000));
        TopicSubscriber sub3 = new TopicSubscriber(new SleepingSubscriber("sub3", 5000));

        queue.subscribe(sub1, topic1);
        queue.subscribe(sub2, topic1);
        queue.subscribe(sub3, topic2);

        queue.publish(topic1, new Message("m1"));
        queue.publish(topic1, new Message("m2"));

        queue.publish(topic2, new Message("m3"));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));
        sub1.stopConsuming();
        sub2.stopConsuming();
        sub3.stopConsuming();
        // queue.resetOffset(topic1, sub1, 0);        
    }
}
