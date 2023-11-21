package solidPrinciple.solid.src.Liskov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vehicle>ls=new ArrayList<>();
        ls.add(new Bicycle());
        ls.add(new Bike());
        for(Vehicle vh:ls){
            vh.getWheels();
        }
    }    
}
