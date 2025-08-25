package pushBased;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        PubSubService pubSubService = PubSubService.getInstance();

        // --- Create Subscribers ---
        Subscriber sportsFan1 = new NewsSubscriber("SportsFan1");
        Subscriber sportsFan2 = new NewsSubscriber("SportsFan2");
        Subscriber techie1 = new NewsSubscriber("Techie1");
        Subscriber allNewsReader = new NewsSubscriber("AllNewsReader");

        // --- Create Topics and Subscriptions ---
        final String SPORTS_TOPIC = "SPORTS";
        final String TECH_TOPIC = "TECH";
        final String WEATHER_TOPIC = "WEATHER";

        pubSubService.createTopic(SPORTS_TOPIC);
        pubSubService.createTopic(TECH_TOPIC);
        pubSubService.createTopic(WEATHER_TOPIC);

        pubSubService.subscribe(SPORTS_TOPIC, sportsFan1);
        pubSubService.subscribe(SPORTS_TOPIC, sportsFan2);
        pubSubService.subscribe(SPORTS_TOPIC, allNewsReader);

        pubSubService.subscribe(TECH_TOPIC, techie1);
        pubSubService.subscribe(TECH_TOPIC, allNewsReader);

        System.out.println("\n--- Publishing Messages ---");

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

        // --- Publish to SPORTS topic ---
        // pubSubService.publish(SPORTS_TOPIC, new Message("Team A wins the championship!"));
        // // Expected: SportsFan1, SportsFan2, AllNewsReader, SystemAdmin receive this.

        // // --- Publish to TECH topic ---
        // pubSubService.publish(TECH_TOPIC, new Message("New AI model released."));
        // // Expected: Techie1, AllNewsReader receive this.

        // // --- Publish to WEATHER topic (no subscribers) ---
        // pubSubService.publish(WEATHER_TOPIC, new Message("Sunny with a high of 75°F."));
        // // Expected: Message is dropped.


        // Allow some time for async messages to be processed
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // --- Shutdown the service ---
        pubSubService.shutdown();    
        publishers.shutdownNow();    
    }
}
