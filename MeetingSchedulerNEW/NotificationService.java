package MeetingSchedulerNEW;

import java.util.List;

class NotificationService {
    public void sendNotification(List<User> users, String message) {
        for (User user : users) {
            System.out.println("📩 Notification sent to: " + user.getEmail() + " - " + message);
        }
    }
}