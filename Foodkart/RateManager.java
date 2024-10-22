package Foodkart;
import java.util.*;


public class RateManager {
    Integer rating=-1;
    List<String>comments;
    RateCalculator calculator=new RateCalculator() {
        
    };
    Integer ratingCount=0;
    public Integer calculate(Integer rate){
        System.out.println("asdas");
        Integer r=calculator.calculate(rating,rate,ratingCount);
        System.out.println("r is "+r);
        this.rating=r;
        this.ratingCount++;
        return r;
    }
    public void rate(Integer rate, String comment){
        comments.add(comment);
        calculate(rate);
        System.out.println("rated");
    }
    @Override
    public String toString() {
        return "RateManager [rating=" + this.rating + ", comments=" + comments + ", calculator=" + calculator
                + ", ratingCount=" + ratingCount + "]";
    }
    public void rate(Integer rate){
        calculate(rate);
        System.out.println("rated");
    }
}
