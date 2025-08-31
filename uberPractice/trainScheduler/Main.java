import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args) {
        TrainScheduler scheduler = new TrainScheduler(3);

        Train t1 = new Train("T1", LocalTime.of(10, 0), Duration.ofMinutes(36));
        Train t2 = new Train("T2", LocalTime.of(10, 15), Duration.ofMinutes(20));
        Train t3 = new Train("T3", LocalTime.of(10, 20), Duration.ofMinutes(25));
        Train t4 = new Train("T4", LocalTime.of(10, 5), Duration.ofMinutes(15));
        Train t5 = new Train("T5", LocalTime.of(10, 5), Duration.ofMinutes(15));
        
        scheduler.scheduleTrain(t1);
        scheduler.scheduleTrain(t2);
        scheduler.scheduleTrain(t3);
        scheduler.scheduleTrain(t4);
        scheduler.scheduleTrain(t5);
        
        LocalTime queryTime = LocalTime.of(10, 16);
        int queryPlatform = 2;
        Train trainAtPlatform = scheduler.getTrainAt(queryPlatform, queryTime);
        if (trainAtPlatform != null) {
            System.out.println("Train at platform " + queryPlatform + " at " + queryTime + " is " + trainAtPlatform.id);
        } else {
            System.out.println("No train at platform " + queryPlatform + " at " + queryTime);
        }        
    }
}