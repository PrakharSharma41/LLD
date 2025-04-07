package producerConsumer;

public class Main {
    public static void main(String[] args) {
        Resource sharedResource=new SharedResource1(10);

        for(int i=0;i<5;i++){
            new Thread(new Producer(sharedResource, i)).start();
            new Thread(new Consumer(sharedResource, i+10)).start();
            try{
                Thread.sleep(2000);
            }catch(Exception e){}
        }
    }
}
