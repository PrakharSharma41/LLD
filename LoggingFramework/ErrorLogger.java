package LoggingFramework;

public class ErrorLogger extends AbstractLogger{
    public ErrorLogger(AbstractLogger nextAbstractLogger){
        super(nextAbstractLogger);
    }
    public void process(LogLevel logLevel,String msg,LoggerTargetObservable loggerTargetObservable){
        if(logLevel.getId()!=3){
            nextLogger.process(logLevel, msg,loggerTargetObservable);
        }else{
            System.out.println("msg: "+msg+" is processed in error logger");
            loggerTargetObservable.notify(logLevel, msg);
        }
    }
}
