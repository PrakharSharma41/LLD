package CustomExecutorService;

public class Main {
    public static void main(String[] args) {
        CustomExecutorService customExecutorService=new CustomExecutorService();
        customExecutorService.submitTask(()->{
            System.out.println(Thread.currentThread().getName()+" is running");
            try{
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+" completed");

            }catch(Exception e){}
        });
        customExecutorService.submitTask(()->{
            System.out.println(Thread.currentThread().getName()+" is running");
            try{
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" completed");

            }catch(Exception e){}
        });

        try{
            Thread.sleep(5000);
            customExecutorService.shutdown();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
