
public class Main {
    public static void main(String[] args) {
        // TokenBucketRateLimiter x=new TokenBucketRateLimiter(0, 0);
        RateLimiter rateLimiter=new SlidingWindowRateLimiterWithCredits(3,3000,4);
        String[] clientIds = {"user1"};
        for(String client:clientIds){
            for(int i=0;i<5;i++){
                new Thread(()->{
                    boolean allowed = rateLimiter.allowRequest(client);
                    // System.out.println(" [" + client + "] -> " + 
                    //                    (allowed ? "Allowed" : "Blocked"));                    
                }).start();
            }    
        }
    }
}
