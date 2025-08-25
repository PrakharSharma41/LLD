import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scheduler scheduler=new Scheduler();
        scheduler.addRoom("room1");
        scheduler.addRoom("room2");
        // scheduler.addRoom("room3");

        LocalDateTime startTime=LocalDateTime.of(2025, 8, 25, 12, 30);
        LocalDateTime endTime=LocalDateTime.of(2025, 8, 25, 13, 30);

        Interval interval=new Interval(startTime,endTime);
        boolean meeting1Result=scheduler.bookMeeting(interval, "meeting1", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting1Result);

        startTime=LocalDateTime.of(2025, 8, 25, 12, 30);
        endTime=LocalDateTime.of(2025, 8, 25, 13, 30);

        interval=new Interval(startTime,endTime);
        boolean meeting2Result=scheduler.bookMeeting(interval, "meeting2", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting2Result);

        startTime=LocalDateTime.of(2025, 8, 25, 14, 30);
        endTime=LocalDateTime.of(2025, 8, 25, 15, 0);

        interval=new Interval(startTime,endTime);
        boolean meeting3Result=scheduler.bookMeeting(interval, "meeting3", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting3Result);

        startTime=LocalDateTime.of(2025, 8, 25, 13, 50);
        endTime=LocalDateTime.of(2025, 8, 25, 16, 55);

        interval=new Interval(startTime,endTime);
        boolean meeting4Result=scheduler.bookMeeting(interval, "meeting4", Arrays.asList(new User("user1", "email1")));
        System.out.println(meeting4Result);



        // boolean meeting2Result=scheduler.bookMeeting(interval, "meeting2", Arrays.asList(new User("user1", "email1")));

        // System.out.println(meeting2Result);

        // startTime=LocalDateTime.of(2025, 8, 25, 13, 40);
        // endTime=LocalDateTime.of(2025, 8, 25, 13, 50);
        // interval=new Interval(startTime,endTime);

        // boolean meeting3Result=scheduler.bookMeeting(interval, "meeting3", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting3Result);

        // boolean meeting4Result=scheduler.bookMeeting(interval, "meeting4", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting4Result);

        // boolean meeting5Result=scheduler.bookMeeting(interval, "meeting5", Arrays.asList(new User("user1", "email1")));
        // System.out.println(meeting5Result);

    }
}
