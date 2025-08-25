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
}
