import java.time.Duration;
import java.time.LocalTime;

public class Train {
    String id;
    @Override
    public String toString() {
        return "Train [id=" + id + ", time=" + time + ", duration=" + duration + "]";
    }
    LocalTime time;
    Duration duration;
    Train(String id,LocalTime time,Duration duration){
        this.id=id;
        this.time=time;
        this.duration=duration;
    }
}
