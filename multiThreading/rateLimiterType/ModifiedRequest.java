package rateLimiterType;

public class ModifiedRequest extends Request{
    String browser;

    public ModifiedRequest(long timeStamp,String ip,String browser) {
        super(timeStamp, ip);
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
}
