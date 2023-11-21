public class ErrorLogProcessor extends LogProcessor{

    public ErrorLogProcessor(LogProcessor nextProcessor) {
        super(nextProcessor);
    }

    public void log(int level, String message){
        if(level == LogProcessor.ERROR){
            System.out.println(message);
        }else{
            super.log(level, message);
        }
    }
    
}
 