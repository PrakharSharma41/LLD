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
    public boolean bookMeeting(Interval interval,String description,List<User>users){
        MeetingRoom room=bookingStrategy.findRoom(List.copyOf(rooms.values()), interval);
        if(room==null)return false;
        System.out.println(room +" booked for "+ interval );
        return room.bookMeeting(interval, description, users);
    }
}
