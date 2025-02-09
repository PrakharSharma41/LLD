package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
class MeetingRoom {
    private String roomName;
    private List<Meeting> bookedMeetings;
    private ReentrantLock lock=new ReentrantLock();
    public MeetingRoom(String roomName) {
        this.roomName = roomName;
        this.bookedMeetings = new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        lock.lock();
        try{
            for (Meeting meeting : bookedMeetings) {
                if (!(end.isBefore(meeting.getStartTime()) || start.isAfter(meeting.getEndTime()))) {
                    return false; // Overlapping meeting
                }
            }
            return true;    
        }finally{
            lock.unlock();
        }
    }

    public void bookMeeting(Meeting meeting) {
        lock.lock(); // Ensure atomic operation
        try {
            bookedMeetings.add(meeting);
            bookedMeetings.sort(Comparator.comparing(Meeting::getStartTime));
        } finally {
            lock.unlock();
        }
    }
    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public List<int[]> getFreeSlots(LocalDateTime dayStart, LocalDateTime dayEnd) {
        List<int[]> freeSlots = new ArrayList<>();
        LocalDateTime currentStart = dayStart;

        for (Meeting meeting : bookedMeetings) {
            if (currentStart.isBefore(meeting.getStartTime())) {
                freeSlots.add(new int[]{(int) currentStart.getHour(), (int) meeting.getStartTime().getHour()});
            }
            currentStart = meeting.getEndTime();
        }

        if (currentStart.isBefore(dayEnd)) {
            freeSlots.add(new int[]{(int) currentStart.getHour(), (int) dayEnd.getHour()});
        }
        return freeSlots;
    }    
}
