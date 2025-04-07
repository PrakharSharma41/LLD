package producerConsumer;

public class Producer implements Runnable{

    private Resource sharedResource;
    private int producerId;
    
    public Producer(Resource sharedResource, int producerId) {
        this.sharedResource = sharedResource;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        int itemId=0;
        while(true){
            sharedResource.produce(producerId * 100 + itemId++);
            try{
                Thread.sleep(2000);
            }catch(Exception e){
                
            }
        }
    }
    
}
