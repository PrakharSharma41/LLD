package MeetingSchedulerNEW;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class User {
    private String userId;
    private String email;
    private Deque<Meeting> meetingHistory; // Stores last 20 meetings

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
        this.meetingHistory = new LinkedList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<Meeting> getMeetingHistory() {
        return List.copyOf(meetingHistory); // Return as immutable list
    }

    public void addMeeting(Meeting meeting) {
        if (meetingHistory.size() >= 20) {
            meetingHistory.removeFirst(); // Remove the oldest meeting
        }
        meetingHistory.addLast(meeting);
    }

    @Override
    public String toString() {
        return "User: " + email + ", Total Meetings: " + meetingHistory.size();
    }
}
