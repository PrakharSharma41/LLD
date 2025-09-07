import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {
    int floorCount;
    ParkingFloor[] floors;
    SpotSearchStrategy strategy;
    Map<String,Ticket>ticketMap;
    AtomicInteger ticketCounter = new AtomicInteger(0);
    ParkingLot(int floorCount){
        this.floorCount=floorCount;
        floors=new ParkingFloor[floorCount];
        for(int i=0;i<floorCount;i++)floors[i]=new ParkingFloor(i);
        strategy=new SearchStrategyImpl(floors);
        ticketMap=new ConcurrentHashMap<>();
    }
    public void addParkingSpot(int floorId,int spotId,VehicleType vehicleType){
        if(floorId>=floorCount)return;
        floors[floorId].addSpot(vehicleType, spotId);
    }
    public String parkVehicle(Vehicle vehicle){
        ParkingSpot spot=strategy.findSpot(vehicle.vehicleType);
        if(spot==null)return "-1";
        synchronized(spot){
            if (!spot.isAvailable()) {   // check again under lock
                return "-1"; // spot already taken by another thread
            }            
            int floorId=spot.floorId;
            floors[floorId].park(spot, vehicle);
            String ticketId="TICKET-" + ticketCounter.incrementAndGet();
            Ticket ticket=new Ticket(ticketId, spot);
            ticketMap.put(ticketId, ticket);
            return ticketId;    
        }
    }
    public void unParkVehicle(String ticketId){
        Ticket ticket=ticketMap.get(ticketId);
        if(ticket==null)return;
        ParkingSpot spot=ticket.spot;
        synchronized(spot){
            int floorId=spot.floorId;
            floors[floorId].unpark(spot);
            ticketMap.remove(ticketId);    
        }
    }
}
