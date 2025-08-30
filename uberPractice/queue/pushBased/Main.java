import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        QueueManager manager=new QueueManager();
        String q1="q1";
        String q2="q2";
        manager.createQueueIfAbsent(q1);
        manager.createQueueIfAbsent(q2);

        Producer p1=new Producer("p1", q1, manager);
        Producer p2=new Producer("p2", q2, manager);
        Producer p3=new Producer("p3", q2, manager);

        Consumer c1=new Consumer("c1", q1, manager);
        Consumer c2=new Consumer("c2", q2, manager);
        Consumer c3=new Consumer("c3", q2, manager);
        ExecutorService producerService=Executors.newFixedThreadPool(3);
        ExecutorService consumerService=Executors.newFixedThreadPool(3);


        producerService.submit(()->{
            for(int i=0;i<5;i++){
                try{
                    Message message=new Message(p1.id, p1.id+"-msg-"+i);
                    System.out.println(message+" published");
                    p1.publish(message);
                    Thread.sleep(2000);
                }catch(Exception e){
                }        
            }
        });
        producerService.submit(()->{
            for(int i=0;i<5;i++){
                try{
                    Message message=new Message(p2.id, p2.id+"-msg-"+i);
                    System.out.println(message+" published");
                    p2.publish(message);
                    Thread.sleep(2000);
                }catch(Exception e){
                }        
            }
        });
        producerService.submit(()->{
            for(int i=0;i<5;i++){
                try{
                    Message message=new Message(p3.id, p3.id+"-msg-"+i);
                    System.out.println(message+" published");
                    p3.publish(message);
                    Thread.sleep(2000);
                }catch(Exception e){
                }        
            }
        });

        consumerService.submit(()->{
            while(!consumerService.isShutdown()){
                try{
                    c1.consume();
                    Thread.sleep(2000);
                }catch(Exception e){}
            }
        });
        consumerService.submit(()->{
            while(!consumerService.isShutdown()){
                try{
                    c2.consume();
                    Thread.sleep(2000);
                }catch(Exception e){}
            }
        });
        consumerService.submit(()->{
            while(!consumerService.isShutdown()){
                try{
                    c3.consume();
                    Thread.sleep(2000);
                }catch(Exception e){}
            }
        });






        try{
            Thread.sleep(8000);
        }catch(Exception e){
        }
        producerService.shutdownNow();
        consumerService.shutdownNow();
    }
}
