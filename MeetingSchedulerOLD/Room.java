package MeetingSchedulerOLD;

import java.util.*;

public class Room {
    private String id;
    private int capacity;
    private TreeMap<Integer, Integer> schedule; // Start time to End time
    private List<String> auditLogs;

    @Override
    public String toString() {
        return "Room [id=" + id + "]";
    }

    public Room(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.schedule = new TreeMap<>();
        this.auditLogs = new ArrayList<>();
    }

    public boolean canAccommodate(Meeting meeting) {
        if (meeting.getRequiredCapacity() > capacity) return false;

        for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
            if (!(meeting.getEndTime() <= entry.getKey() || meeting.getStartTime() >= entry.getValue())) {
                return false; // Overlapping
            }
        }
        return true;
    }

    public void addMeeting(Meeting meeting) {
        schedule.put(meeting.getStartTime(), meeting.getEndTime());
        auditLogs.add("Meeting " + meeting.getId() + " scheduled from " +
                meeting.getStartTime() + " to " + meeting.getEndTime());
    }

    public void cleanupLogs(int days) {
        // Implement logic to delete logs older than 'x' days (e.g., using a timestamp).
        // For simplicity, clearing all logs here.
        auditLogs.clear();
    }

    public int getSpillageScore(Meeting meeting) {
        int spillage = 0;
        for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
            if (meeting.getStartTime() >= entry.getKey() && meeting.getEndTime() <= entry.getValue()) {
                spillage += (entry.getValue() - entry.getKey()) - (meeting.getEndTime() - meeting.getStartTime());
            }
        }
        return spillage;
    }

    // Getters and other methods
}
