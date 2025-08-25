import java.util.List;

public interface BookingStrategy {
    MeetingRoom findRoom(List<MeetingRoom>rooms,Interval interval);
}
