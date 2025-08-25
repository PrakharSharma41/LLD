package pullBased;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        PubSubService pubSubService=new PubSubService();
        Consumer consumer1=new ConsumerImpl("c1");
        Consumer consumer2=new ConsumerImpl("c2");
        Consumer consumer3=new ConsumerImpl("c3");

        final String SPORTS_TOPIC = "SPORTS";
        final String TECH_TOPIC = "TECH";
        final String WEATHER_TOPIC = "WEATHER";

        pubSubService.createTopic(SPORTS_TOPIC);
        pubSubService.createTopic(TECH_TOPIC);
        pubSubService.createTopic(WEATHER_TOPIC);

        pubSubService.subscribe(SPORTS_TOPIC, consumer1);
        pubSubService.subscribe(TECH_TOPIC, consumer2);
        pubSubService.subscribe(WEATHER_TOPIC, consumer3);

        ExecutorService publishers=Executors.newFixedThreadPool(3);

        publishers.submit(() -> {
            pubSubService.publish(SPORTS_TOPIC, new Message("Team A wins the championship!"));
            pubSubService.publish(SPORTS_TOPIC, new Message("Player X traded to Team B."));
        });

        publishers.submit(() -> {
            pubSubService.publish(TECH_TOPIC, new Message("New AI model released."));
            pubSubService.publish(TECH_TOPIC, new Message("Quantum computer breakthrough."));
        });

        publishers.submit(() -> {
            pubSubService.publish(WEATHER_TOPIC, new Message("Sunny with a high of 75°F."));
            pubSubService.publish(WEATHER_TOPIC, new Message("Rain expected tomorrow."));
        });

        ExecutorService consumers=Executors.newFixedThreadPool(3);

        consumers.submit(()->{
            pubSubService.pull(consumer1,SPORTS_TOPIC);
        });
        publishers.shutdownNow();
        consumers.shutdownNow();
    }
}
