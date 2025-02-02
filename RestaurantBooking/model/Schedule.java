package RestaurantBooking.model;

public class Schedule {
    int startTime,endTime,availableSeats;
    public Schedule(int startTime, int endTime,int availableSeats) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSeats=availableSeats;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "Schedule [startTime=" + startTime + ", endTime=" + endTime + ", availableSeats=" + availableSeats + "]";
    }

    public void setAvailableSeats(int seats) {
        this.availableSeats -= seats;
    }
    
}
