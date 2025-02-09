package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
       public static void main(String[] args) {
        MeetingRoom room1 = new MeetingRoom("Room A");
        MeetingRoom room2 = new MeetingRoom("Room B");
        List<MeetingRoom> rooms = Arrays.asList(room1, room2);

        NotificationService notificationService = new NotificationService();

        MeetingScheduler scheduler = new MeetingScheduler(rooms, notificationService, new MinSpillageRoomStrategy());

        User alice = new User("U1", "alice@example.com");
        User bob = new User("U2", "bob@example.com");

        Runnable task1 = () -> scheduler.bookMeeting(alice, "Team Sync", LocalDateTime.of(2025, 2, 10, 10, 0), LocalDateTime.of(2025, 2, 10, 11, 0), Arrays.asList(bob));
        Runnable task2 = () -> scheduler.bookMeeting(bob, "Project Review", LocalDateTime.of(2025, 2, 10, 11, 0), LocalDateTime.of(2025, 2, 10, 12, 0), Arrays.asList(alice));

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n🌍 Global Meeting History:");
        for (Meeting meeting : scheduler.getGlobalMeetingHistory()) {
            System.out.println(meeting);
        }
    }
}
