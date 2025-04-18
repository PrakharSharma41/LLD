package entities;

import strategy.DiceStrategy;

public class Dice {
    int diceCount;
    final int min=1;
    final int max=6;
    public DiceStrategy diceStrategy;
    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }
    public int rollDice(){
        int totalSum=0;
        int diceUsed=0;
        // use dice strategy here
        while(diceUsed<diceCount){
            int count=(int)(Math.random()*(max)+1);
            totalSum+=count;
            diceUsed++;
        }
        return totalSum;
    }        
}
