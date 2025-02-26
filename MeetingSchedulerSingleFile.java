import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

class Meeting{
    LocalDateTime startTime;
    LocalDateTime endTime;
    public Meeting(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }    
}

class MeetingRoom{
    String name;
    private List<Meeting> bookedMeetings;
    public MeetingRoom(String name) {
        this.name = name;
        bookedMeetings=new ArrayList<>();
        Deque<int[]>q=new LinkedList<>();
        
    }
    @Override
    public String toString() {
        return "MeetingRoom [name=" + name + "]";
    }    
    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        try{
            for (Meeting meeting : bookedMeetings) {
                if (!(end.isBefore(meeting.getStartTime()) || start.isAfter(meeting.getEndTime()))) {
                    return false; // Overlapping meeting
                }
            }
            return true;    
        }finally{

        }
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
class MeetingScheduler{
    List<MeetingRoom>rooms;
    public MeetingScheduler(List<MeetingRoom> rooms) {
        this.rooms = rooms;
        TreeMap<Integer,Integer>mp=new TreeMap<>();
        
    }
    MeetingRoom findRoom(LocalDateTime startTime,LocalDateTime endTime){
        MeetingRoom bestRoom = null;
        int minSpillage = Integer.MAX_VALUE;

        for (MeetingRoom room : rooms) {
            if (room.isAvailable(startTime, endTime)) {
                int spillage = calculateSpillage(room, startTime, endTime);
                if (spillage < minSpillage) {
                    minSpillage = spillage;
                    bestRoom = room;
                }
            }
        }
        return bestRoom;
    }
    private int calculateSpillage(MeetingRoom room, LocalDateTime start, LocalDateTime end) {
        List<int[]> freeSlots = room.getFreeSlots(LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 0, 0),
                                                  LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 23, 59));
        int spillage = Integer.MAX_VALUE;

        for (int[] slot : freeSlots) {
            int slotStart = slot[0], slotEnd = slot[1];
            int wasteBefore = Math.abs(start.getHour() - slotStart);
            int wasteAfter = Math.abs(slotEnd - end.getHour());

            int totalWaste = wasteBefore + wasteAfter;
            if (totalWaste < spillage) {
                spillage = totalWaste;
            }
        }
        return spillage;
    }    
}
public class MeetingSchedulerSingleFile {
    public static void main(String[] args) {
        List<MeetingRoom>rooms=new ArrayList<>(Arrays.asList(new MeetingRoom("room1"),new MeetingRoom("room2"),
        new MeetingRoom("room3")));
        MeetingScheduler meetingScheduler=new MeetingScheduler(rooms);
        System.out.println(meetingScheduler.findRoom(LocalDateTime.of(2025, 2, 10, 10, 0), LocalDateTime.of(2025, 2, 10, 11, 0)));
    }
}
