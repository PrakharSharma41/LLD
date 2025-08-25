public class Main {
    public static void main(String[] args) {
        ExpiryCounter counter = new ExpiryCounter(2000); // TTL = 2 seconds

        counter.addCounter("apple");
        counter.addCounter("apple");
        counter.addCounter("banana");

        System.out.println("apple count: " + counter.getCounter("apple")); // 2
        System.out.println("total count: " + counter.getTotalCounter());   // 3

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // wait > ttl
        // counter.addCounter("banana");
        System.out.println("apple count: " + counter.getCounter("apple")); // 0
        System.out.println("total count: " + counter.getTotalCounter());   // 0  
        counter.cleaner.shutdownNow();      
    }
}
