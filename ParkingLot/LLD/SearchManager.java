package ParkingLot.LLD;

import java.util.concurrent.ConcurrentHashMap;

public class SearchManager {
    private ConcurrentHashMap<String, ParkingResult> cache= new ConcurrentHashMap<>();

    void index(ParkingResult result){   // add prefixes to indiacate whether its spotId or vehicleId or ticketId
        cache.put(result.getTicketId(), result);
    }

    public ParkingResult searchVehicle(String ticketId){
        return cache.get(ticketId);
    }    
}
