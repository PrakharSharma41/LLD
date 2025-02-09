package MeetingSchedulerOLD;

import java.util.List;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager(List<Room> rooms) {
        this.rooms = rooms;
    }

    public synchronized boolean scheduleMeeting(Meeting meeting) {
        Room bestRoom = null;
        int minSpillage = Integer.MAX_VALUE;
        for (Room room : rooms) {
            if (room.canAccommodate(meeting)) {
                // System.out.println("kjabsljnsa");
                int spillage = room.getSpillageScore(meeting);
                if (spillage < minSpillage) {
                    minSpillage = spillage;
                    bestRoom = room;
                }
            }
        }

        if (bestRoom != null) {
            bestRoom.addMeeting(meeting);
            System.out.println(bestRoom+" room booked for "+meeting);
            return true;
        }
        System.out.println("no room available for "+meeting);
        return false; // No room available
    }

    public void cleanupLogs(int days) {
        for (Room room : rooms) {
            room.cleanupLogs(days);
        }
    }
}
