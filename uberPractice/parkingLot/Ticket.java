public class Ticket {
    ParkingSpot spot;
    String ticketId;
    Ticket(String ticketId,ParkingSpot spot){
        this.ticketId=ticketId;
        this.spot=spot;
    }
}
