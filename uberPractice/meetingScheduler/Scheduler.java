import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {
    ConcurrentHashMap<String,MeetingRoom>rooms;
    BookingStrategy bookingStrategy;
    Scheduler(){
        rooms=new ConcurrentHashMap<>();
        bookingStrategy=new MinSpillageStrategyImpl();
    }
    public void addRoom(String roomName){
        rooms.putIfAbsent(roomName, new MeetingRoom(roomName));
    }
    public boolean bookMeeting(LocalTime startTime,LocalTime endTime,String description,List<User>users){
        MeetingRoom room=bookingStrategy.findRoom(List.copyOf(rooms.values()), startTime,endTime);
        if(room==null)return false;
        System.out.println(room +" booked for "+ startTime+" " +endTime);
        return room.bookMeeting(startTime,endTime, description, users);
    }
}
