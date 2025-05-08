abstract class LogProcessor {
    public static final int DEBUG=1;
    public static final int ERROR=2;
    public static final int INFO=3;
    LogProcessor nextProcessor;
    public LogProcessor(LogProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }
    public void log(int loglevel, String message) {
        System.out.println("in abstract class");
        if(nextProcessor != null){
            nextProcessor.log(loglevel, message);
        }
    }    
}
