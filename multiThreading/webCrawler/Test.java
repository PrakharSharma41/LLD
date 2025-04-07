package webCrawler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(3000); // simulates a 3s delay
            return 10;
        });

        System.out.println("Waiting for result...");
        int result=0;
        System.out.println("khbsnidjnbnm,mnhbgnjhnjmnha");
        try {
            result = future.get();
        } catch (Exception e) {}
        System.out.println("Result: " + result);
        System.out.println("khbsnidjna");
    }
}
