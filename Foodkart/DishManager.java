package Foodkart;
import java.util.*;

public class DishManager {
    private List<Food>food;

    public DishManager(List<Food> food) {
        this.food = food;
    }

    public List<Food> getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "DishManager [food=" + food + "]";
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
    // public Food getFood(Food f){
    //     for(int i=0;i<food.size();i++){
    //         if(food.get(i).foodName==f.foodName){
    //             return food.get(i);
    //         }
    //     }
    //     return null;
    // }
}
