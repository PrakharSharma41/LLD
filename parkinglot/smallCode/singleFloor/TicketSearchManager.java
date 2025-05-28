package singleFloor;

import java.util.HashMap;
import java.util.Map;

import singleFloor.entities.ParkingSpot;

public class TicketSearchManager {
    Map<Integer,ParkingSpot>ticketMap;

    public TicketSearchManager() {
        ticketMap=new HashMap<>();
    }
    public ParkingSpot getParkingSpotFromTicket(int ticketId){
        return ticketMap.get(ticketId);
    }

    public void putTicket(int ticketId,ParkingSpot spot){
        ticketMap.put(ticketId, spot);
    }
}
