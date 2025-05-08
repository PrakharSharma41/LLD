public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        LogProcessor obj=new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
        obj.log(LogProcessor.DEBUG,"message debugging");
        obj.log(LogProcessor.INFO,"message info");
    }
}
