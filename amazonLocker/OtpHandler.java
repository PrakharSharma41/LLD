package amazonLocker;

public class OtpHandler {
    public String generateOtp(){
        return String.valueOf((int)(Math.random()*10000));
    }
    public boolean validateOtp(String systemOtp,String userOtp){
        return true;//assume that it is always true
    }
}
