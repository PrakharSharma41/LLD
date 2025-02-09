package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class MeetingScheduler {
    private List<MeetingRoom> rooms;
    private NotificationService notificationService;
    private MeetingRepository meetingRepository;
    private RoomBookingStrategy bookingStrategy;
    private final ReentrantLock schedulerLock = new ReentrantLock(); 
    public MeetingScheduler(List<MeetingRoom> rooms, NotificationService notificationService, RoomBookingStrategy bookingStrategy) {
        this.rooms = rooms;
        this.notificationService = notificationService;
        this.meetingRepository = MeetingRepository.getInstance();
        this.bookingStrategy = bookingStrategy;
    }

    public void setBookingStrategy(RoomBookingStrategy strategy) {
        this.bookingStrategy = strategy;
    }

    public Meeting bookMeeting(User user, String title, LocalDateTime startTime, LocalDateTime endTime, List<User> attendees) {
        MeetingRoom room = bookingStrategy.findRoom(rooms, startTime, endTime);
        if (room == null) {
            System.out.println("⚠️ No available rooms for the requested time slot.");
            return null;
        }
    
        room.lock(); // Lock only the specific room
        try {
            Meeting meeting = new Meeting(title, startTime, endTime, room, attendees, user);
            room.bookMeeting(meeting);
            meetingRepository.addMeeting(meeting);
            user.addMeeting(meeting);
            notificationService.sendNotification(attendees, "Meeting Scheduled: " + meeting);
            return meeting;
        } finally {
            room.unlock(); // Unlock after booking
        }
    }
    
    public List<Meeting> getGlobalMeetingHistory() {
        return meetingRepository.getLastMeetings();
    }
}