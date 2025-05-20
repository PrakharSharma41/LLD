package com.example.game.entities;

import java.util.HashSet;

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

    // public Point validPoint(Point newHead){
    //     if(newHead.getY()==width){
    //         return new Point(newHead.getX(),0);
    //     }
    //     else if(newHead.getY()<0){
    //         return new Point(newHead.getX(),width-1);
    //     }
    //     else if(newHead.getX()==height){
    //         return new Point(0,newHead.getY());
    //     }
    //     else if(newHead.getY()<0){
    //         return new Point(height-1,newHead.getY());
    //     }
    //     return newHead;
    // }

    public boolean isFoodPoint(Point p){
        boolean foodPresent=food.contains(p);
        System.out.println("foodpresent is "+foodPresent+" at "+p);
        if(foodPresent)food.remove(p);
        return foodPresent;
    }
}
