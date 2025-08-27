package normal;

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(new Task("1",System.currentTimeMillis() + 4000,4000,() -> System.out.println("Task 1 executed")));
        scheduler.addTask(new Task("2", System.currentTimeMillis() + 1000,2000,() -> System.out.println("Task 2 executed")));
        scheduler.addTask(new Task("3", System.currentTimeMillis() + 7000,1000,() -> System.out.println("Task 3 executed")));

        new Thread(()->scheduler.runScheduler()).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scheduler.stop();        
    }
}
