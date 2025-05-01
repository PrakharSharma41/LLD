package com.example.interview.entities;

import java.util.Objects;

public class Point {
    int x,y;
    public Point(int x,int y){
        this.x=x;this.y=y;
    }
    public int getX() {
        return x;
    }
    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public int hashCode(){
        return Objects.hash(x,y);
    }
    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        Point point=(Point)o;
        if(this.x==point.x&&this.y==point.y){
            return true;
        }
        return false;
    }    
}
