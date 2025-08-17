package ticTacToe.enums;

public enum Piece {
    X('X'),
    O('O'),
    EMPTY('_');
    Piece(char c){
        this.c=c;
    }
    char c;
    public char getChar(){
        return c;
    }
}
