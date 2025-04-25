public class Booking {

    int bookingId;
    BookingSlot bookingSlot;
    Vehicle vehicle;
    public Booking(int bookingId, BookingSlot bookingSlot, Vehicle vehicle) {
        this.bookingId = bookingId;
        this.bookingSlot = bookingSlot;
        this.vehicle = vehicle;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingSlot=" + bookingSlot + ", vehicle=" + vehicle + "]";
    }
    public BookingSlot getBookingSlot() {
        return bookingSlot;
    }
    public void setBookingSlot(BookingSlot bookingSlot) {
        this.bookingSlot = bookingSlot;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
