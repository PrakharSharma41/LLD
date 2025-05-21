package com.example.interview;

import com.example.interview.entities.Board;
import com.example.interview.entities.Direction;
import com.example.interview.entities.Point;
import com.example.interview.entities.Snake;

public class SnakeGame {
    Board board;
    Snake snake;
    int inputCount=1;
    public SnakeGame(int width,int height){
        board=new Board(width, height);
        snake=new Snake();
    }
    public void addSnakeBody(Point p){
        snake.setSnakeBody(p);
    }
    public void moveSnake(String dir){
        Direction direction=Direction.fromString(dir);
        inputCount++;
        Point oldHead=snake.getHead();
        int[] move=direction.move(oldHead.getX(), oldHead.getY());
        Point newHead=new Point(move[0],move[1]);
        newHead=board.validPoint(newHead);
        boolean grow=false;
        if(inputCount==5){
            grow=true;
            inputCount=0;
        }
        snake.move(newHead, grow);
        System.out.println(snake.getHead()+" "+snake.getTail());
        if(isGameOver()){
            return ;
        }
    }
    public boolean isGameOver(){
        return !snake.isLastMovePossible();
    }

}
