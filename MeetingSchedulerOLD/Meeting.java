package MeetingSchedulerOLD;

public class Meeting {
    private String id;
    private int startTime; // In 24-hour format, e.g., 900 for 9:00 AM
    private int endTime;   // In 24-hour format, e.g., 1000 for 10:00 AM
    private int requiredCapacity;


    @Override
    public String toString() {
        return "Meeting [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", requiredCapacity="
                + requiredCapacity + "]";
    }

    // Constructor, getters, and setters
    public Meeting(String id, int startTime, int endTime, int requiredCapacity) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredCapacity = requiredCapacity;
    }

    public int getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    // Getters and other utility methods
}
