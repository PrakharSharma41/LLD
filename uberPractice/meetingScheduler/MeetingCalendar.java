import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.TreeSet;

public class MeetingCalendar {
    TreeSet<Meeting>meetings;
    MeetingCalendar(){
        meetings=new TreeSet<>();
    }
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public boolean checkAvailabality(Interval interval) {
        Meeting before=meetings.floor(new Meeting(interval, null, null));
        Meeting after=meetings.ceiling(new Meeting(interval, null, null));
        if(before!=null&&before.getInterval().endTime.isAfter(interval.startTime))return false;
        if(after!=null&&after.getInterval().startTime.isBefore(interval.endTime))return false;
        return true;
    }
}

// before  after