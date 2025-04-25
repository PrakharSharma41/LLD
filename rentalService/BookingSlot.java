import java.time.LocalDateTime;

public class BookingSlot {
    LocalDateTime starTime;
    LocalDateTime endTime;
    public BookingSlot(LocalDateTime starTime, LocalDateTime endTime) {
        this.starTime = starTime;
        this.endTime = endTime;
    }
    public LocalDateTime getStarTime() {
        return starTime;
    }
    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }
    @Override
    public String toString() {
        return "BookingSlot [starTime=" + starTime + ", endTime=" + endTime + "]";
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public boolean overlaps(BookingSlot other) {
        return starTime.isBefore(other.endTime) && other.starTime.isBefore(endTime);
    }    
}
