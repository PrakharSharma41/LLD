package snakeLadder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {
    Board board;
    Deque<Player>players;
    Player winnerPlayer;
    Dice dice;
    GameStatus gameStatus;
    public Game(int boardSize,List<BoardEntity>entities){
        board=new Board(boardSize, entities);
        players=new LinkedList<>();winnerPlayer=null;
        dice=new Dice(1, 6);
    }
    public void setPlayers(List<String>players){
        for(String s:players){
            Player p=new Player(s);
            this.players.add(p);
        }
    }
    public void play(){
        if(players.size()<2){
            System.out.println("game cannot be played");return ;
        }
        gameStatus=GameStatus.RUNNING;
        while(gameStatus==GameStatus.RUNNING){
            Player currPlayer=players.pollFirst();
            takeTurn(currPlayer);
            if(gameStatus==GameStatus.RUNNING){
                players.add(currPlayer);
            }
        }
        System.out.println(winnerPlayer+" won the game");
    }
    public void takeTurn(Player player){
        int currentPosition=player.getPosition();
        int rollDice=dice.rollDice();
        int finalPosition=currentPosition+rollDice;
        if(finalPosition>board.getSize()){
            System.out.println("need exactly "+(board.getSize()-currentPosition)+" roll");
            return ;
        }
        if(finalPosition==board.getSize()){
            winnerPlayer=player;
            gameStatus=GameStatus.FINISHED;
            player.setPosition(finalPosition);
            return ;
        }
        finalPosition=board.getFinalPosition(finalPosition);
        if(finalPosition>currentPosition){
            System.out.println(player+" found ladder");
        }else if(finalPosition<currentPosition){
            System.out.println(player+" found snake");
        }else{
            System.out.println(player+" is at position "+finalPosition);
        }
        player.setPosition(finalPosition);
        if(rollDice==6){
            System.out.println(player+" has another turn");
            takeTurn(player);
        }
    }
}
