import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OnlineVoting {
    static Map<Integer,Integer>votesAcquired=new HashMap<>();
    static Map<Integer,Integer>personLeading=new HashMap<>();
    static int personLeadingSoFar=-1;
    static List<Integer>timesVotesHaveBeenCast=new ArrayList<>();
    static HashSet<Integer>userVoted=new HashSet<>();
    static void process_vote(int time,int voterId){
        timesVotesHaveBeenCast.add(time);
        votesAcquired.put(voterId,votesAcquired.getOrDefault(voterId, 0)+1);
        if(personLeadingSoFar!=voterId&&votesAcquired.getOrDefault(personLeading, 0)<votesAcquired.get(voterId)){
            personLeadingSoFar=voterId;
        }
        personLeading.put(time, personLeadingSoFar);
    }
    static int getPersonLeadingSoFar(){
        return personLeadingSoFar;
    }
    static int getPersonLeadingAtTimeT(int time){
        return personLeading.get(getTimeEqualToOrLessThan(time));
    }
    static int getTimeEqualToOrLessThan(int time){
        int mid=0,left=0,right=timesVotesHaveBeenCast.size();
        while(left<right){
            mid=left+(right-left)/2;
            if(timesVotesHaveBeenCast.get(mid)==time)return time;
            else if(timesVotesHaveBeenCast.get(mid)>time)right=mid;
            else left=mid+1;
        }
        return timesVotesHaveBeenCast.get(left)<=time?timesVotesHaveBeenCast.get(left):timesVotesHaveBeenCast.get(left-1);
    }
    public static void main(String[] args) {
        timesVotesHaveBeenCast=new ArrayList<>(Arrays.asList(1,2,3,4,6,7,8));
        System.out.println(getTimeEqualToOrLessThan(5));
        List<Integer>candidatesIdRange=new ArrayList<>();
        candidatesIdRange.add(1);candidatesIdRange.add(10);
        Scanner sc=new Scanner(System.in);
        int user_id,candidate_id,time=0,input;
        while(true){
            time++;
            System.out.println("enter user id");
            user_id=sc.nextInt();
            if(userVoted.contains(user_id)){
                System.out.println("you have already voted");continue;
            }
            userVoted.add(user_id);
            System.out.println("Press 1 for exit and 2 to vote ");
            input=sc.nextInt();
            if(input==1)break;
            else{
                System.out.println("enter candidate id to vote for");
                candidate_id=sc.nextInt();
                process_vote(time, candidate_id);
                System.out.println("winning candidate id is "+getPersonLeadingAtTimeT(time));
            }
        }
        sc.close();
    }
}
