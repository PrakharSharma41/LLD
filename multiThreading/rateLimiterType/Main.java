package rateLimiterType;

public class Main {
    public static void main(String[] args) {
        long windowSize = 5000; // 5 seconds
        AttributeExtractorStrategy strategy=new AttributeExtractorStrategyImpl();
        SlidingWindowRequestCounter counter = new SlidingWindowRequestCounterImpl(windowSize,strategy);

        long now = System.currentTimeMillis();
        Request r1 = new ModifiedRequest(now, "192.168.1.1", "Chrome");
        Request r2 = new ModifiedRequest(now, "192.168.1.2", "Firefox");

        counter.recordRequest(r1, GroupBy.IP);
        counter.recordRequest(r2, GroupBy.IP);
        counter.recordRequest(r1, GroupBy.BROWSER);
        counter.recordRequest(r2, GroupBy.BROWSER);

        System.out.println("Count for IP 192.168.1.1: " + counter.getRequestCount("192.168.1.1"));
        System.out.println("Count for browser Chrome: " + counter.getRequestCount("Chrome"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // wait for requests to expire

        System.out.println("After window expiration:");
        System.out.println("Count for IP 192.168.1.1: " + counter.getRequestCount("192.168.1.1"));
        System.out.println("Count for browser Chrome: " + counter.getRequestCount("Chrome"));
    }
}
