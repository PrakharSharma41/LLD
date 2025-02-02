package LoggingFramework;

public class ConsoleLogger implements LogObserver{

    @Override
    public void log(String msg) {
        System.out.println("writing to console "+msg);
    }
    
}
