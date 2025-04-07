package PriorityTaskScheduler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler(3);
        scheduler.scheduleTask(new Task("Low-Priority-Task", Priority.LOW, System.currentTimeMillis(), scheduler.getRunnableTask("Low-Priority-Task")));
        scheduler.scheduleTask(new Task("High-Priority-Task", Priority.HIGH, System.currentTimeMillis(), scheduler.getRunnableTask("High-Priority-Task")));
        scheduler.scheduleTask(new Task("Medium-Priority-Task1", Priority.MEDIUM, System.currentTimeMillis(), scheduler.getRunnableTask("Medium-Priority-Task1")));
        scheduler.scheduleTask(new Task("Medium-Priority-Task2", Priority.MEDIUM, System.currentTimeMillis(), scheduler.getRunnableTask("Medium-Priority-Task2")));
        scheduler.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
