package MeetingSchedulerNEW;

import java.time.LocalDateTime;
import java.util.List;

class MinSpillageRoomStrategy implements RoomBookingStrategy {
    @Override
    public MeetingRoom findRoom(List<MeetingRoom> rooms, LocalDateTime startTime, LocalDateTime endTime) {
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
