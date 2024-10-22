import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class SnakeGame {
    static Deque<int[]>snakeConfiguration=new LinkedList<>();
    static HashSet<String>lookup=new HashSet<>();
    static int foodIndex,width,height;
    static int[][] food;
    public static void start(int w, int h, int[][] f){
        width=w;height=h;food=f;
        foodIndex=0;
        snakeConfiguration.add(new int[]{0,0});
        lookup.add(convertToString(0, 0));
    }
    private static String convertToString(int x, int y) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(x));
        sb.append(", ");
        sb.append(Integer.toString(y));
        return sb.toString();
    }

    public static int move(String direction){
        int[] head=snakeConfiguration.peekFirst();
        int headRow=head[0],headCol=head[1];
        switch(direction){
            case "U":
            headRow--;break;
            case "D":
            headRow++;break;
            case "L":
            headCol--;break;
            case "R":
            headCol++;break;
        }
        int[] tail=snakeConfiguration.peekLast();
        if((lookup.contains(convertToString(headRow, headCol))&&!(headRow==tail[0]&&headCol==tail[1]))|| headRow<0|| headRow>=height||headCol<0||headCol>=width){
            return -1;
        }
        snakeConfiguration.addFirst(new int[]{headRow,headCol});
        lookup.add(convertToString(headRow, headCol));
        if(foodIndex<food.length&&headRow==food[foodIndex][0]&&headCol==food[foodIndex][1]){
            foodIndex++;
        }else{
            snakeConfiguration.removeLast();
            if(!(headRow==tail[0]&&headCol==tail[1])){
                lookup.remove(convertToString(tail[0], tail[1]));
            }
        }
        int[] oldTail = snakeConfiguration.getLast();
        if ((lookup.contains(convertToString(headRow, headCol)) && !(headRow == oldTail[0] && headCol == oldTail[1])) // check if snakes bites itself.
                                                                    // notice that if the new head is at the place of old tail then 
                                                                    // it does not bite itself since the new position of tail is no more at 
                                                                    // the position of old tail and has moved one cell forward 
           || headRow < 0 || headRow >= height || headCol < 0 || headCol >= width) { // checks if the snake hit the wall
            return -1;
        }
        

        return snakeConfiguration.size()-1;
    }
    public static void main(String[] args) {
        int width=100,height=100;
        int[][] food=new int[][]{{4,4},{6,6},{7,7}};
        start(width,height,food);
        Scanner sc=new Scanner(System.in);
        String input;int x;
        while(true){
            x=sc.nextInt();
            sc.nextLine();
            if(x==1)break;
            System.out.println("enter direction");
            input=sc.nextLine();
            if(move(input)==-1){
                System.out.println("game finished");
                break;
            }
        }
        sc.close();
    }    
}
// - - - - - - - - 
// - - - - - - - - 
// - - - - - - - - 
// - - - - - - - - 
// - t h - - - - - 
// - - - - - - - - 