package LoggingFramework;

public class InfoLogger extends AbstractLogger{
    public InfoLogger(AbstractLogger nextAbstractLogger){
        super(nextAbstractLogger);
    }
    public void process(LogLevel logLevel,String msg,LoggerTargetObservable loggerTargetObservable){
        if(logLevel.getId()!=1){
            nextLogger.process(logLevel, msg,loggerTargetObservable);
        }else{
            System.out.println("msg: "+msg+" is processed in info logger");
            loggerTargetObservable.notify(logLevel, msg);
        }
    }
}
