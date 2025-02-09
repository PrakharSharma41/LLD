package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.List;

class FirstAvailableRoomStrategy implements RoomBookingStrategy {
    @Override
    public MeetingRoom findRoom(List<MeetingRoom> rooms, LocalDateTime startTime, LocalDateTime endTime) {
        for (MeetingRoom room : rooms) {
            if (room.isAvailable(startTime, endTime)) {
                return room;
            }
        }
        return null; // No room available
    }
}
