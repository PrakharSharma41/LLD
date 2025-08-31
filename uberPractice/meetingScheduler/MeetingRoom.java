import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingRoom {
    String roomName;
    TreeSet<Meeting>meetings;
    ReentrantLock lock;
    @Override
    public String toString() {
        return "MeetingRoom [roomName=" + roomName + "]";
    }
    public MeetingRoom(String roomName) {
        this.roomName = roomName;
        meetings=new TreeSet<>();
        lock=new ReentrantLock();
    }
    public boolean checkAvailabality(LocalTime startTime,LocalTime endTime){
        Meeting before=meetings.floor(new Meeting(startTime,endTime, null, null));
        Meeting after=meetings.ceiling(new Meeting(startTime,endTime, null, null));
        if(before!=null&&before.endTime.isAfter(startTime))return false;
        if(after!=null&&after.startTime.isBefore(endTime))return false;
        return true;
    }
    public boolean bookMeeting(LocalTime startTime,LocalTime endTime,String description,List<User>users){
        Meeting meeting=new Meeting(startTime,endTime, description, users);
        lock.lock();
        try{
            if(checkAvailabality(startTime,endTime)){
                meetings.add(meeting);
                return true;        
            }else{
                return false;
            }
        }finally{
            lock.unlock();
        }
    }
    public long calculateSpillage(LocalTime startTime,LocalTime endTime) {
        Meeting before = meetings.floor(new Meeting(startTime,endTime, null, null));
        Meeting after = meetings.ceiling(new Meeting(startTime,endTime, null, null));

        // If not available → return -1
        if (!checkAvailabality(startTime,endTime)) {
            return -1;
        }

        // Effective free slot boundaries
        LocalTime slotStart = before == null ? LocalTime.MIN : before.endTime;
        LocalTime slotEnd   = after == null ? LocalTime.MAX : after.startTime;

        long slotDuration = Duration.between(slotStart, slotEnd).toMinutes();
        long meetingDuration = Duration.between(startTime, endTime).toMinutes();

        // Spillage = how much of the free slot is wasted after placing this meeting
        return slotDuration - meetingDuration;
    }    
}
