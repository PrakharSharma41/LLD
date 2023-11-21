import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// space always increases in this approach
class Approach1{
    private static Map<String,Integer> mp=new HashMap<>();
    static boolean shouldPrintMessage(String message,Integer timestamp){
        if(mp.containsKey(message)==false || timestamp-mp.get(message)>10){
            mp.put(message, timestamp);
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true){
            String message=sc.nextLine();
            Integer timeStamp=sc.nextInt();
            System.out.println(message+" "+timeStamp);
            boolean bt=shouldPrintMessage(message,timeStamp);
            System.out.println(message+" print status: "+bt);
            sc.nextLine();
        }
    }
}
