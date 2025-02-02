package LoggingFramework;

public class DebugLogger extends AbstractLogger{
    public DebugLogger(AbstractLogger nextAbstractLogger){
        super(nextAbstractLogger);
    }
    public void process(LogLevel logLevel,String msg,LoggerTargetObservable loggerTargetObservable){
        if(logLevel.getId()!=2){
            nextLogger.process(logLevel, msg,loggerTargetObservable);
        }else{
            System.out.println("msg: "+msg+" is processed in debug logger");
            loggerTargetObservable.notify(logLevel, msg);
        }
    }
}
