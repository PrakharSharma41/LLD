package shortCode.snakeLadder;

import java.util.HashMap;
import java.util.List;

public class Board{
    int size;
    HashMap<Integer,Integer>entity;
    Board(int size,List<BoardEntity>entities){
        this.size=size;
        entity=new HashMap<>();
        if(entities!=null){
            for(BoardEntity b:entities){
                entity.put(b.start, b.end);
            }    
        }
    }
    public int getSize(){
        return size;
    }
    public int getFinalPosition(int currentPosition){
        int finalPosition=entity.getOrDefault(currentPosition, currentPosition);
        return finalPosition;
    }
}