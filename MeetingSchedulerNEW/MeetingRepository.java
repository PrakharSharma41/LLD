package MeetingSchedulerNEW;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class MeetingRepository {
    private static MeetingRepository instance;
    private final Deque<Meeting> meetingHistory;

    private MeetingRepository() {
        meetingHistory = new LinkedList<>();
    }

    public static synchronized MeetingRepository getInstance() {
        if (instance == null) {
            instance = new MeetingRepository();
        }
        return instance;
    }

    public void addMeeting(Meeting meeting) {
        meetingHistory.addLast(meeting);
        if (meetingHistory.size() > 20) {
            meetingHistory.removeFirst(); // Keep only last 20 meetings globally
        }
    }

    public List<Meeting> getLastMeetings() {
        return new ArrayList<>(meetingHistory);
    }
}