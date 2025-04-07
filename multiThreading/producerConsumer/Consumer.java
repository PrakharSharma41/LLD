package producerConsumer;

public class Consumer implements Runnable{
    Resource sharedResource;
    int consumerId;
    
    public Consumer(Resource sharedResource, int consumerId) {
        this.sharedResource = sharedResource;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        while(true){
            sharedResource.consume();
            try{
                Thread.sleep(2000);
            }catch(Exception e){}
        }
    }
    
}
