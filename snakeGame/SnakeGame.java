import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SnakeGame {
    Board board;
    Snake snake;
    int score;
    SnakeGame(int width,int height,List<int[]>food){
        // board=new Board(width, height,convertToPoint(food));
        board=new Board(width, height,convertToPointHashSet(food));
        snake=new Snake();
    }
    public HashSet<Point> convertToPointHashSet(List<int[]>foodList){
        // List<Point>food1=foodList.stream().map((int[] ind)->new Point(ind[0],ind[1])).collect(Collectors.toList());
        HashSet<Point>food=new HashSet<>();
        for(int[] point:foodList){
            food.add(new Point(point[0], point[1]));
        }
        return food;
    }
    public List<Point> convertToPoint(List<int[]>foodList){
        // List<Point>food1=foodList.stream().map((int[] ind)->new Point(ind[0],ind[1])).collect(Collectors.toList());
        List<Point>food=new ArrayList<>();
        for(int[] point:foodList){
            food.add(new Point(point[0], point[1]));
        }
        return food;
    }
    public int move(String dir){
        Direction direction=Direction.fromString(dir);
        Point head=snake.getHead();
        int[] moved=direction.move(head.getX(),head.getX());
        Point newHead=new Point(moved[0], moved[1]);
        if(!board.validPoint(newHead))return -1;
        boolean grow=board.isFoodPoint(newHead);
        snake.move(newHead, grow);
        score++;
        return score;
    }
}
