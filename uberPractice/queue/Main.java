import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        QueueManager manager = new QueueManager();

        // Create producers
        Producer producerA = new Producer("Queue1", manager, "ProducerA");
        Producer producerB = new Producer("Queue1", manager, "ProducerB");
        Producer producerC = new Producer("Queue2", manager, "ProducerC");

        // Create consumers
        Consumer consumer1 = new Consumer("Queue1", manager, "Consumer1");
        Consumer consumer2 = new Consumer("Queue1", manager, "Consumer2");
        Consumer consumer3 = new Consumer("Queue2", manager, "Consumer3");


        ExecutorService executor = Executors.newCachedThreadPool();
        
        executor.execute(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    producerA.publish("Message " + i);
                    Thread.sleep(300);
                }
            } catch (Exception e) {}
        });

        executor.execute(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    producerB.publish("Message " + i);
                    Thread.sleep(500);
                }
            } catch (Exception e) {}
        });

        executor.execute(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    producerC.publish("Message " + i);
                    Thread.sleep(700);
                }
            } catch (Exception e) {}
        });

        // Run consumers in threads (continuous)
        executor.execute(() -> {
            try {
                while (!executor.isShutdown()) consumer1.consume();
            } catch (Exception e) {}
        });

        executor.execute(() -> {
            try {
                while (!executor.isShutdown()) consumer2.consume();
            } catch (Exception e) {}
        });

        executor.execute(() -> {
            try {
                while (!executor.isShutdown()) consumer3.consume();
            } catch (Exception e) {}
        });

        // Let it run for a while then shut down
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executor.shutdownNow();        
    }
}
