package flyweightDesign;

import java.util.HashMap;
import java.util.Map;

public class RoboticFactoy {
    private static Map<String,IRobot>mp=new HashMap<>();
    public static IRobot createRobot(String robotType){
        if(mp.containsKey(robotType)){
            return mp.get(robotType);
        }else{
            if(robotType=="Humanoid"){
                Sprites humanoidSprites=new Sprites();
                IRobot humanoidObject=new HumanoidRobot(robotType, humanoidSprites);
                mp.put(robotType, humanoidObject);
                return humanoidObject;
            }
            return null;
        }
    }
}
