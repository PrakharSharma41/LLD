import java.time.LocalTime;
import java.util.List;

public class BookingStrategyImpl implements BookingStrategy{

    @Override
    public MeetingRoom findRoom(List<MeetingRoom> rooms, LocalTime startTime,LocalTime endTime) {
        for(MeetingRoom room:rooms){
            if(room.checkAvailabality(startTime,endTime))return room;
        }
        return null;
    }
    
}
