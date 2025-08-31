import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainScheduler {
    List<Platform>platforms;
    TrainScheduler(int size){
        platforms=new ArrayList<>();
        for(int i=0;i<size;i++){
            platforms.add(new Platform(i));
        }
    }
    public void scheduleTrain(Train train){
        for(Platform platform:platforms){
            if(platform.isPlatformAvailable(train.time, train.duration)){
                platform.scheduleTrain(train);
                return;
            }
        }
        Platform earliestPlatform = platforms.get(0);
        LocalTime earliestTime = earliestPlatform.getNextAvailableTime(train.time, train.duration);

        for (Platform platform : platforms) {
            LocalTime nextAvailable = platform.getNextAvailableTime(train.time, train.duration);
            if (nextAvailable.isBefore(earliestTime)) {
                earliestTime = nextAvailable;
                earliestPlatform = platform;
            }
        }
        train.time=earliestTime;
        earliestPlatform.scheduleTrain(train);        
    }
    public Train getTrainAt(int platformNumber,LocalTime time){
        Platform platform=platforms.get(platformNumber);
        return platform.getTrainAt(time);
    }
}
