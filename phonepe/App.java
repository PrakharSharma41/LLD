import enums.TaskStatus;
import models.User;
import service.TaskService;
import service.UserService;

public class App {
    public static void main(String[] args) {
        UserService userService=new UserService();
        TaskService taskService=new TaskService(userService);
        User user1=userService.createUser("user1");
        // User user2=userService.createUser("user2");
        // User user3=userService.createUser("user3");

        taskService.createTask("user1", "task1", 1, 2);  // removed
        taskService.createTask("user1", "task2", 3, 10); // created
        taskService.createTask("user1", "task3", 2, 6); // created
        taskService.completeTask("task1");
        taskService.removeTask("task1");

        System.out.println("listing all tasks for user1");
        taskService.showAllTaskForUser("user1");

        System.out.println("applying filter criteria");
        taskService.listTask("user1",TaskStatus.REMOVED);
        System.out.println("below is the activity log");
        taskService.getActivityLog("user1", 2, 7);
    }
}
