package LoggingFramework;

public class Logger {
    private AbstractLogger abstractLogger;
    private LoggerTargetObservable loggerTargetObservable;
    public AbstractLogger chaining(){
        AbstractLogger infoLogger=new InfoLogger(new DebugLogger(new ErrorLogger(abstractLogger)));
        return infoLogger;
    }
    public LoggerTargetObservable addObservers(){
        LoggerTargetObservable loggerTargetObservable=new LoggerTargetObservable();
        ConsoleLogger consoleLogger=new ConsoleLogger();
        FileLogger fileLogger=new FileLogger();
        loggerTargetObservable.addObserver(LogLevel.INFO, consoleLogger);
        loggerTargetObservable.addObserver(LogLevel.DEBUG, fileLogger);
        loggerTargetObservable.addObserver(LogLevel.ERROR, consoleLogger);
        return loggerTargetObservable;
    }
    public Logger() {
        this.abstractLogger=chaining();
        this.loggerTargetObservable=addObservers();
    }
    public void inputInfoLog(String msg){
        abstractLogger.process(LogLevel.INFO, msg, loggerTargetObservable);
    }
    public void inputDebugLog(String msg){
        abstractLogger.process(LogLevel.DEBUG, msg, loggerTargetObservable);
    }
    public void inputErrorLog(String msg){
        abstractLogger.process(LogLevel.ERROR, msg, loggerTargetObservable);
    }
}
