package LoggingFramework;

public abstract class AbstractLogger {
    public AbstractLogger nextLogger;

    public AbstractLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }   
    public void process(LogLevel logLevel,String msg,LoggerTargetObservable loggerTargetObservable){
        if(nextLogger!=null){
            nextLogger.process(logLevel, msg,loggerTargetObservable);
        }
    }
}
