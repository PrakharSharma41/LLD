package amazonLocker;


public class Locker {
    int id;
    boolean available;
    User user;
    long timeBooked;
    long expiryTime;
    public Locker(int id, boolean available) {
        this.id = id;
        this.available = available;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getId() {
        return id;
    }
    public void setTimeBooked(long timeBooked) {
        this.timeBooked = timeBooked;
    }
    public boolean isAvailable() {
        return available;
    }
    public User getUser() {
        return user;
    }
    public long getTimeBooked() {
        return timeBooked;
    }
    public long getExpiryTime() {
        return expiryTime;
    }
    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
    public void freeLocker(){
        setUser(null);
        setAvailable(true);
    }
    public void bookLocker(User user,long bookTime){
        long expiryTime=bookTime+5000;
        setUser(user);
        setAvailable(false);
        setTimeBooked(bookTime);
        setExpiryTime(expiryTime);
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean isLockerExpired(long currentTime){
        return this.expiryTime<=currentTime;   
    }
}
