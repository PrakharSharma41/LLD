import java.time.LocalTime;
import java.util.List;

public interface BookingStrategy {
    MeetingRoom findRoom(List<MeetingRoom>rooms,LocalTime startTime,LocalTime endTime);
}
