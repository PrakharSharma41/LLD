package MeetingSchedulerOLD;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        Room room1 = new Room("Room1", 16);
        // Room room2 = new Room("Room2", 15);
        // Room room3 = new Room("Room3", 20);

        RoomManager roomManager = new RoomManager(Arrays.asList(room1));

        // Schedule some meetings
        Meeting meeting1 = new Meeting("M1", 0, 1000, 5);
        Meeting meeting2 = new Meeting("M2", 1800, 2000, 10);
        // ExecutorService executor = Executors.newFixedThreadPool(10);
        // Runnable runnable=()->{
        //     String threadName = Thread.currentThread().getName();
        //     for(int i=0;i<5;i++){
        //         int startTime=(int)(Math.random()*1500);
        //         int endTime=startTime+(int)(Math.random()*900);
        //         Meeting meeting=new Meeting(threadName, 100, 200,(int) (Math.random()*15));
        //         roomManager.scheduleMeeting(meeting);
        //     }
        // };
        // for(int i=0;i<5;i++){
        //     executor.submit(runnable);
        // }
        // executor.shutdown();
        // try {
        //     executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        System.out.println(roomManager.scheduleMeeting(meeting1)); // true
        System.out.println(roomManager.scheduleMeeting(meeting2)); // true

        // Start periodic cleanup
        // AuditLogCleaner logCleaner = new AuditLogCleaner(roomManager, 7);

        // Add shutdown hook for cleanup
        // Runtime.getRuntime().addShutdownHook(new Thread(logCleaner::stop));
    }
}
