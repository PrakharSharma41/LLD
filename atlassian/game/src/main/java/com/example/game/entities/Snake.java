package com.example.game.entities;


import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {
    Set<String>bodySet;
    Deque<Point>snake;
    public Snake(){
        bodySet=new HashSet<>();
        snake=new LinkedList<>();
        Point start = new Point(0, 0);
        bodySet.add("0_0");
        snake.add(start);
    }
    public boolean move(Point newHead,boolean grow){
        Point tail;
        if(!grow){
            tail=snake.removeLast();
            bodySet.remove(tail.getX()+"_"+tail.getY());
        }
        String headKey=newHead.getX()+"_"+newHead.getY();

        if(bodySet.contains(headKey)){
            return false;
        }
        snake.addFirst(newHead);
        bodySet.add(headKey);
        return true;
    }
    public Point getHead(){
        return snake.getFirst();
    }
}
