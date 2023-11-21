import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;



class Approach4{
    static class Message{
        int timeStamp;
        String message;
        public Message(int timeStamp,String message){
            this.message=message;
            this.timeStamp=timeStamp;
        }
    }
    private static Map<String,Integer>current=new HashMap<>();
    private static Map<String,Integer>previous=new HashMap<>();
    private static final int MAX_TIME_WINDOW = 10;
    private static int initialTimeStamp = 0;

    static boolean shouldPrintMessage(String message,Integer timestamp){
        if(timestamp>initialTimeStamp+20){ // 20=2*MAX_TIME_WINDOW
            current.clear();
        }
        if(timestamp>initialTimeStamp+10){
            previous=current;
            current=new HashMap<>();
            initialTimeStamp=timestamp;
        }
        if(previous.getOrDefault(message, -1)>timestamp || current.getOrDefault(message, -1)>timestamp){
            return false;
        }
        current.put(message, timestamp+10);
        return true;
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
