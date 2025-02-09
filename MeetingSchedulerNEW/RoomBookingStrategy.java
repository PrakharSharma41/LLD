package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.List;

interface RoomBookingStrategy {
    MeetingRoom findRoom(List<MeetingRoom> rooms, LocalDateTime startTime, LocalDateTime endTime);
}
