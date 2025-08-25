import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingRoom {
    String roomName;
    MeetingCalendar calendar;
    ReentrantLock lock;
    @Override
    public String toString() {
        return "MeetingRoom [roomName=" + roomName + "]";
    }
    MailClient mailClient;
    public MeetingRoom(String roomName) {
        this.roomName = roomName;
        calendar=new MeetingCalendar();
        lock=new ReentrantLock();
    }
    public boolean checkAvailabality(Interval interval){
        return calendar.checkAvailabality(interval);
    }
    public boolean bookMeeting(Interval interval,String description,List<User>users){
        Meeting meeting=new Meeting(interval, description, users);
        lock.lock();
        try{
            if(checkAvailabality(interval)){
                calendar.addMeeting(meeting);
                return true;        
            }else{
                return false;
            }
        }finally{
            lock.unlock();
        }
    }
    public long calculateSpillage(Interval interval) {
        Meeting before = calendar.meetings.floor(new Meeting(interval, null, null));
        Meeting after = calendar.meetings.ceiling(new Meeting(interval, null, null));

        // If not available → return -1
        if (!checkAvailabality(interval)) {
            return -1;
        }

        // Effective free slot boundaries
        LocalDateTime slotStart = before == null ? LocalDateTime.MIN : before.getInterval().endTime;
        LocalDateTime slotEnd   = after == null ? LocalDateTime.MAX : after.getInterval().startTime;

        long slotDuration = Duration.between(slotStart, slotEnd).toMinutes();
        long meetingDuration = Duration.between(interval.startTime, interval.endTime).toMinutes();

        // Spillage = how much of the free slot is wasted after placing this meeting
        return slotDuration - meetingDuration;
    }    
}
