import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Board {
    int width,height;
    // List<Point>food;
    HashSet<Point>food;
    public Board(int width, int height,HashSet<Point>food) {
        this.width = width;
        this.height = height;
        this.food=food;
    }
    public boolean validPoint(Point newHead){
        if(newHead.getX()<0||newHead.getY()<0||newHead.getX()>=height||newHead.getY()>=width){
            return false;
        }
        return true;
    }
    public boolean isFoodPoint(Point p){
        boolean foodPresent=food.contains(p);
        if(foodPresent)food.remove(p);
        return foodPresent;
    }
}
