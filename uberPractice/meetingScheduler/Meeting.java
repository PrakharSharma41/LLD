import java.util.Comparator;
import java.util.List;

public class Meeting implements Comparable<Meeting>{
    Interval interval;
    String description;
    List<User>user;
    public Meeting(Interval interval, String description,List<User>users) {
        this.interval = interval;
        this.description = description;
        this.user=users;
    }
    public Interval getInterval() {
        return interval;
    }
    public void setInterval(Interval interval) {
        this.interval = interval;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }
    @Override
    public int compareTo(Meeting o) {
        int cmp = this.interval.startTime.compareTo(o.interval.startTime);
        if (cmp != 0) return cmp;
        cmp = this.interval.endTime.compareTo(o.interval.endTime);
        if (cmp != 0) return cmp;
        return this.description.compareToIgnoreCase(o.description);
    }
    
}