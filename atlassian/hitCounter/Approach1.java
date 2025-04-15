package hitCounter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Approach1 {
    Deque<Integer>q=new LinkedList<>();
    public void hit(Integer timeStamp){
        q.addFirst(timeStamp);
    }
    public int getHits(Integer timeStamp){
        while(!q.isEmpty()&&timeStamp-q.peekLast()>=300){
            q.pollLast();
        }
        return q.size();
    }
}
// 4 3 2 1