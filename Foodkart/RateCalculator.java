package Foodkart;

public interface RateCalculator {
    public default Integer calculate(){
        return 5;
    };
    public default Integer calculate(Integer current,Integer newR,Integer count){
        if(current==-1)return newR;
        else return ((current*count)+newR)/(count+1);
    }
}
