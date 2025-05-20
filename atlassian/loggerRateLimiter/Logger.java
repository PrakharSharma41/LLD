import java.util.HashMap;

public class Logger{
    HashMap<String,Integer>messageToTime;
    Logger(){
        messageToTime=new HashMap<>();
    }
    public boolean shouldPrintMessage(String message,int time){
        int latestTime=messageToTime.getOrDefault(message, -1000);
        if(time-latestTime<=10)return false;
        messageToTime.put(message, time);
        return true;
    }
}