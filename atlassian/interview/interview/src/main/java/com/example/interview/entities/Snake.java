package com.example.interview.entities;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {
    Set<Point>bodySet;
    Deque<Point>snake;
    boolean lastMovePossible=true;
    public Snake(){
        bodySet=new HashSet<>();
        snake=new LinkedList<>();
        Point p=new Point(10, 10);
        snake.add(p);
        bodySet.add(p);
        p=new Point(9, 10);
        bodySet.add(p);
        snake.add(p);
        p=new Point(8, 10);
        bodySet.add(p);
        snake.add(p);
    }
    public boolean move(Point newHead,boolean grow){
        Point tail;
        if(!grow){
            tail=snake.removeLast();
            bodySet.remove(tail);    
        }
        // if(bodySet.contains(newHead)){
        //     return false;
        // }
        snake.addFirst(newHead);
        if(!bodySet.add(newHead)){
            lastMovePossible=false;
        }
        return true;
    }
    public Point getHead(){
        return snake.peekFirst();
    }
    public Point getTail(){
        return snake.peekLast();
    }
    public boolean isLastMovePossible() {
        return lastMovePossible;
    }
    
}
//"RIGHT","RIGHT","RIGHT","UP","LEFT","LEFT","LEFT","LEFT","DOWN","RIGHT","UP"
// 10,11 10,10 9,10