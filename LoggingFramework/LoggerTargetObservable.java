package LoggingFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoggerTargetObservable {
    private HashMap<LogLevel,List<LogObserver>>observerList=new HashMap<>();
    public void addObserver(LogLevel logLevel,LogObserver logObserver){
        observerList.putIfAbsent(logLevel, new ArrayList<>());
        observerList.get(logLevel).add(logObserver);
        // if(!observerList.containsKey(logLevel)){
        //     observerList.put(logLevel, logObserver);
        // }
    }
    public void removeObserver(LogLevel logLevel){
        // remove log observer code here
    }
    public void notify(LogLevel logLevel,String msg){
        // observerList.get(logLevel).log(msg);
        for(LogObserver obs:observerList.get(logLevel)){
            obs.log(msg);
        }   
    }
}
