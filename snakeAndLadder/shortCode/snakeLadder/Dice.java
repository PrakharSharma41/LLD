package shortCode.snakeLadder;

public class Dice {
    int minValue;
    int maxValue;
    public Dice(int minValue,int maxValue){
        this.maxValue=maxValue;this.minValue=minValue;
    }
    public int rollDice(){
        return (int)(Math.random()*(maxValue-minValue+1))+minValue;
    }
}
