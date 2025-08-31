import java.time.LocalTime;
import java.util.List;

public class MinSpillageStrategyImpl implements BookingStrategy{
    @Override
    public MeetingRoom findRoom(List<MeetingRoom> rooms, LocalTime startTime,LocalTime endTime) {
        MeetingRoom bestRoom = null;
        long minSpillage = Long.MAX_VALUE;

        for (MeetingRoom room : rooms) {
            long spillage = room.calculateSpillage(startTime,endTime);
            if (spillage >= 0 && spillage < minSpillage) {
                minSpillage = spillage;
                bestRoom = room;
            }
        }

        return bestRoom;
    }    
}
