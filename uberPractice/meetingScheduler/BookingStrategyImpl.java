import java.util.List;

public class BookingStrategyImpl implements BookingStrategy{

    @Override
    public MeetingRoom findRoom(List<MeetingRoom> rooms, Interval interval) {
        for(MeetingRoom room:rooms){
            if(room.checkAvailabality(interval))return room;
        }
        return null;
    }
    
}
