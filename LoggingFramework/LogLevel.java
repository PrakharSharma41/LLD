package LoggingFramework;

public enum LogLevel {
    INFO(1),ERROR(3),DEBUG(2);
    private int id;
    private LogLevel(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
}
