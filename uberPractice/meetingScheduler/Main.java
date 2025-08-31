import java.time.LocalTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Scheduler scheduler=new Scheduler();
        scheduler.addRoom("room1");
        scheduler.addRoom("room2");
        // scheduler.addRoom("room3");

        LocalTime startTime=LocalTime.of(12, 30);
        LocalTime endTime=LocalTime.of(13, 30);

        boolean meeting1Result=scheduler.bookMeeting(startTime,endTime, "meeting1", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting1Result);

        startTime=LocalTime.of( 12, 30);
        endTime=LocalTime.of(13, 30);

        boolean meeting2Result=scheduler.bookMeeting(startTime,endTime, "meeting2", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting2Result);

        startTime=LocalTime.of(14, 30);
        endTime=LocalTime.of( 15, 0);

        boolean meeting3Result=scheduler.bookMeeting(startTime,endTime, "meeting3", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting3Result);

        startTime=LocalTime.of( 14, 20);
        endTime=LocalTime.of(16, 55);

        boolean meeting4Result=scheduler.bookMeeting(startTime,endTime, "meeting4", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting4Result);


        startTime=LocalTime.of( 13, 50);
        endTime=LocalTime.of(13, 55);

        boolean meeting5Result=scheduler.bookMeeting(startTime,endTime, "meeting5", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting5Result);


// room1:  
// 12:30 to 13:30   14:30 to 15:00

// room2:
// 12:30 to 13:30   14:20 to 16:55

        // boolean meeting2Result=scheduler.bookMeeting(interval, "meeting2", Arrays.asList(new User("user1", "email1")));

        // System.out.println(meeting2Result);

        // startTime=LocalTime.of(2025, 8, 25, 13, 40);
        // endTime=LocalTime.of(2025, 8, 25, 13, 50);
        // interval=new Interval(startTime,endTime);

        // boolean meeting3Result=scheduler.bookMeeting(interval, "meeting3", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting3Result);

        // boolean meeting4Result=scheduler.bookMeeting(interval, "meeting4", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting4Result);

        // boolean meeting5Result=scheduler.bookMeeting(interval, "meeting5", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting5Result);

    }
}
