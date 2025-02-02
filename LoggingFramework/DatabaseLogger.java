package LoggingFramework;

public class DatabaseLogger implements LogObserver{
    @Override
    public void log(String msg) {
        System.out.println("writing to database "+msg);
    }
}
