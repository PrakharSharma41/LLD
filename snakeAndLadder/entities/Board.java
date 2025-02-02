package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board{
    Cell[][]cells;
    int boardSize;
    public Board(int size,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions){
        boardSize=size;
        createBoard(size);
        addSnakeAndLadderToBoard(numberOfSnakes, snakesPositions, numberOfLadders, laddersPositions);
    }
    private void addSnakeAndLadderToBoard(int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions) {
        while(numberOfSnakes>0){
            int snakeStart=(int)(Math.random()*(cells.length*cells.length));
            int snakeEnd=(int)(Math.random()*(cells.length*cells.length));            
            // int snakeStart=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(snakeEnd>snakeStart)continue;
            Jump snakeHead=new Jump(snakeStart,snakeEnd);
            Cell cells=getCell(snakeStart);
            cells.jump=snakeHead;
            numberOfSnakes--;
        }
        while(numberOfLadders>0){
            int ladderStart=(int)(Math.random()*(cells.length*cells.length));
            int ladderEnd=(int)(Math.random()*(cells.length*cells.length));            
            // int snakeStart=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(ladderStart>ladderEnd)continue;
            Jump snakeHead=new Jump(ladderStart,ladderEnd);
            Cell cells=getCell(ladderStart);
            cells.jump=snakeHead;
            numberOfLadders--;
        }
    }
    private void createBoard(int size){
        cells=new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j]=new Cell();
            }
        }        
    }
    public Cell getCell(int position){
        int row=position/cells.length;
        int column=position%cells.length;
        return cells[row][column];
    }
}

