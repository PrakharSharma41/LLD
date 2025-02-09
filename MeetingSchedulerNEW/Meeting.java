package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.List;
class Meeting {
    private static int counter = 1;
    private int meetingId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MeetingRoom room;
    private List<User> attendees;
    private User bookedBy;

    public Meeting(String title, LocalDateTime startTime, LocalDateTime endTime, MeetingRoom room, List<User> attendees, User bookedBy) {
        this.meetingId = counter++;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.attendees = attendees;
        this.bookedBy = bookedBy;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    @Override
    public String toString() {
        return "Meeting ID: " + meetingId + ", Title: " + title + ", Room: " + room.getRoomName() + 
               ", Start: " + startTime + ", End: " + endTime + ", Booked by: " + bookedBy.getEmail();
    }
}