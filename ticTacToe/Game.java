package ticTacToe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import ticTacToe.enums.Piece;
import ticTacToe.model.Board;
import ticTacToe.model.Player;
import ticTacToe.strategy.WinStrategy;

public class Game {
    Board board;
    Deque<Player>players;
    List<WinStrategy>strategy;
    Player winnerPlayer=null;
    Game(int row,int col,List<WinStrategy>strategy){
        board=new Board(row,col);
        players=new LinkedList<>();
        this.strategy=strategy;
    }
    public void addPlayer(Piece piece,String name){
        Player player=new Player(piece, name);
        players.add(player);
    }
    public void startGame(){
        Player currentPlayer=null;
        while(!isGameOver(currentPlayer)){
            board.printBoard();
            currentPlayer=players.removeFirst();
            makeMove(currentPlayer);
            players.addLast(currentPlayer);
        }
        if(winnerPlayer==null)System.out.println("game draw");
        else System.out.println(winnerPlayer+" won the game");
    }
    public boolean isGameOver(Player player){
        boolean gameOver=false;
        if(player==null)return false;
        System.out.println("player is "+player);
        for(WinStrategy s:strategy){
            if(s.checkWinner(board, player)){
                gameOver=true;
                winnerPlayer=player;break;
            }
        }
        if(board.isBoardFull())gameOver=true;
        return gameOver;
    }
    public void makeMove(Player player){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter row and col, example: a b");
        int row=-1,col=-1;
        while(true){
            try{
                String input=br.readLine();
                String[] parsedInput=input.split(" ");
                row=Integer.parseInt(parsedInput[0]);
                col=Integer.parseInt(parsedInput[1]);
                if(board.makeMove(row,col,player.getPiece())){
                    break;
                }
                else{
                    throw new Exception("try another move");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

}
