import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;


// leaking bucket
class Approach1{
    static class Message{
        int timeStamp;
        String message;
        public Message(int timeStamp,String message){
            this.message=message;
            this.timeStamp=timeStamp;
        }
    }
    private static Queue<Message>qt=new LinkedList<>();
    private static final int MAX_TIME_WINDOW = 10;
    static boolean shouldPrintMessage(String message,Integer timestamp){
        while(!qt.isEmpty()&& timestamp-qt.peek().timeStamp>=MAX_TIME_WINDOW){
            qt.poll();
        }
        Iterator<Message>it=qt.iterator();
        while(it.hasNext()){
            Message msg=it.next();
            if (msg.message.equals(message)) return false;            
        }
        qt.offer(new Message( timestamp,message));
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
