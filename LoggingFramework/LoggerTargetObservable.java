package LoggingFramework;

import java.util.HashMap;

public class LoggerTargetObservable {
    private HashMap<LogLevel,LogObserver>observerList=new HashMap<>();
    public void addObserver(LogLevel logLevel,LogObserver logObserver){
        if(!observerList.containsKey(logLevel)){
            observerList.put(logLevel, logObserver);
        }
    }
    public void removeObserver(LogLevel logLevel){
        // remove log observer code here
    }
    public void notify(LogLevel logLevel,String msg){
        observerList.get(logLevel).log(msg);
        // for(LogObserver obs:observerList.values()){
        //     obs.log(msg);
        // }   
    }
}
