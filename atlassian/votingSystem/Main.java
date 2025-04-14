  

public class Main {
    public static void main(String[] args) {

        GroupManager groupManager = new GroupManager();
        Runnable vote = () -> System.out.println(Thread.currentThread().getName() + " voted.");
        

        for (int i = 0; i < 6; i++) {
            Party p = (i % 2 == 0) ? Party.DEMOCRAT : Party.REPUBLICAN;
            new Thread(new Voter(p, groupManager, vote), p + "-Thread-" + i).start();
        }        
    }    
}
