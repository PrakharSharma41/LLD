package com.example.game;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.example.game.entities.Board;
import com.example.game.entities.Point;
import com.example.game.entities.Snake;


public class SnakeGame {
    Board board;
    Snake snake;
    int score;
    public SnakeGame(int width,int height,List<int[]>food){
        // board=new Board(width, height,convertToPoint(food));
        board=new Board(width, height,convertToPointHashSet(food));
        snake=new Snake();
    }
    public HashSet<Point> convertToPointHashSet(List<int[]>foodList){
        HashSet<Point>food=new HashSet<>();
        for(int[] point:foodList){
            food.add(new Point(point[0], point[1]));
        }
        return food;
    }
    public int move(String dir){
        Direction direction=Direction.fromString(dir);
        Point head=snake.getHead();
        int[] moved=direction.move(head.getX(),head.getY());
        Point newHead=new Point(moved[0], moved[1]);
        if(!board.validPoint(newHead))return -1;
        boolean grow=board.isFoodPoint(newHead);
        boolean possible=snake.move(newHead, grow);
        if(possible==false)return -1;
        if(grow){
            score++;
        }
        return score;
    }
}
