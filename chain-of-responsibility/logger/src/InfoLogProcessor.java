public class InfoLogProcessor extends LogProcessor{

    public InfoLogProcessor(LogProcessor nextProcessor) {
        super(nextProcessor);
    }

    public void log(int level, String message){
        System.out.println("in info");
        if(level == LogProcessor.INFO){
            System.out.println(message);
        }else{
            super.log(level, message);
        }
    }
    
}
