public class DebugLogProcessor extends LogProcessor {

    public DebugLogProcessor(LogProcessor nextProcessor) {
        super(nextProcessor);
    }
    public void log(int level, String message){
        if(level == LogProcessor.DEBUG){
            System.out.println(message);
        }else{
            super.log(level, message);
        }
    }

    
}
