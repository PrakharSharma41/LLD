import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Platform {
    TreeMap<LocalTime,Train>trainMap;
    Integer id;
    Platform(Integer id){
        this.id=id;
        trainMap=new TreeMap<>();
    }
    boolean isPlatformAvailable(LocalTime time,Duration duration){
        Map.Entry<LocalTime,Train>etr=trainMap.floorEntry(time);
        if(etr!=null){
            Train floorTrain=etr.getValue();
            Duration floorDuration=floorTrain.duration;
            if(floorTrain.time.plus(floorDuration).isAfter(time))return false;
        }
        Map.Entry<LocalTime,Train>ceil=trainMap.ceilingEntry(time);
        if(ceil!=null){
            Train ceilingTrain=ceil.getValue();
            if(time.plus(duration).isAfter(ceilingTrain.time))return false;
        }
        return true;
    }
    public LocalTime getNextAvailableTime(LocalTime arrival, Duration duration) {
        LocalTime time = arrival;
        while (!isPlatformAvailable(time, duration)) {
            time = time.plusMinutes(1);
        }
        return time;
    }    
    void scheduleTrain(Train train){
        trainMap.put(train.time, train);
        System.out.println(train+" scheduled at platform "+id );
    }
    Train getTrainAt(LocalTime time){
        Map.Entry<LocalTime, Train> floor = trainMap.floorEntry(time);
        if (floor != null) {
            LocalTime start = floor.getKey();
            LocalTime end = start.plus(floor.getValue().duration);
            if (!time.isBefore(start) && time.isBefore(end)) {
                return floor.getValue();
            }
        }
        return null;        
    }
}
