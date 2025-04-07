package StampedLock.readWrite;

public class Test {
    public static void main(String[] args) {
        SharedResource sharedResource=new SharedResource();
        Thread producer=new Thread(()->{
            System.out.println("producer thread is "+Thread.currentThread().getName());
            // while(true){
                sharedResource.producer();
            // }
        });
        Thread consumer=new Thread(()->{
            System.out.println("consumer thread is "+Thread.currentThread().getName());
            // while(true){
                sharedResource.consume();
            // }
        });
        producer.start();
        consumer.start();
    }    
}
