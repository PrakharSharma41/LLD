package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board{
    public Cell[][]cells;
    int boardSize;
    public Board(int size,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions){
        boardSize=size;
        createBoard(size);
        addSnakeAndLadderToBoard(numberOfSnakes, snakesPositions, numberOfLadders, laddersPositions);
    }
    private void addSnakeAndLadderToBoard(int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions) {
        if(snakesPositions!=null&&numberOfSnakes>0){
            for(int i=0;i<snakesPositions.length;i++){
                int snakeStart=snakesPositions[i][0];
                int snakeEnd=snakesPositions[i][1];
                Jump snake=new Jump(snakeStart,snakeEnd);
                Cell cells=getCell(snakeStart);
                cells.jump=snake;    
            }
        }
        if(laddersPositions!=null&&numberOfLadders>0){
            for(int i=0;i<laddersPositions.length;i++){
                int ladderStart=laddersPositions[i][0];
                int ladderEnd=laddersPositions[i][1];            
                Jump ladderHead=new Jump(ladderStart,ladderEnd);
                Cell cells=getCell(ladderStart);
                cells.jump=ladderHead;
            }
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
        int size = cells.length;
        int row = position / size;
        int col = position % size;
        if (row >= size || col >= size) return null;
        return cells[row][col];
    }
    public int getBoardSize(){
        return boardSize*boardSize;
    }
}

