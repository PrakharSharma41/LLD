package rateLimiterType;

public class Request {
    long timeStamp;
    String ipAddress;
    public long getTimeStamp() {
        return timeStamp;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public Request(long timeStamp, String ipAddress) {
        this.timeStamp = timeStamp;
        this.ipAddress = ipAddress;
    }
    
}
