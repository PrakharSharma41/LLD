public class Seats {
    SeatType seatType;
    SeatStatus seatStatus;
    int seatNumber;
    public Seats(SeatType seatType, SeatStatus seatStatus,int seatNumber) {
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.seatNumber=seatNumber;
    }
    public void reserve(){
        seatStatus=SeatStatus.BOOKED;
    }
    public boolean isAvailable(){
        return seatStatus==SeatStatus.AVAILABLE;
    }
    public void release(){
        seatStatus=SeatStatus.AVAILABLE;
    }
}
