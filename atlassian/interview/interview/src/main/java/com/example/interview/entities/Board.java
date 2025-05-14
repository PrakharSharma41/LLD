package com.example.interview.entities;

public class Board {
    int width,height;
    public Board(int width,int height){
        this.width=width;
        this.height=height;
    }
    public Point validPoint(Point newHead){
        if(newHead.getY()==width){
            return new Point(newHead.getX(),0);
        }
        else if(newHead.getY()<0){
            return new Point(newHead.getX(),width-1);
        }
        else if(newHead.getX()==height){
            return new Point(0,newHead.getY());
        }
        else if(newHead.getY()<0){
            return new Point(height-1,newHead.getY());
        }
        return newHead;
    }

}

// 2,0 2,1 2,2
// 1,0 1,1 1,2  
// 0,0 0,1 0,2