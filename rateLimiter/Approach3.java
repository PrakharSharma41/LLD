import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;

class Approach3{
    static class Message{
        int timeStamp;
        String message;
        public Message(int timeStamp,String message){
            this.message=message;
            this.timeStamp=timeStamp;
        }
    }
    private static Queue<Message>qt=new LinkedList<>();
    private static Set<String> lookup=new HashSet<>();
    private static final int MAX_TIME_WINDOW = 10;
    static boolean shouldPrintMessage(String message,Integer timestamp){
        while(!qt.isEmpty()&& timestamp-qt.peek().timeStamp>=MAX_TIME_WINDOW){
            lookup.remove(qt.peek().message);
            qt.poll();
        }
        if(lookup.contains(message))return false;
        lookup.add(message);
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
