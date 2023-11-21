package bridge;

public class Main {
    public static void main(String[] args) {
        LivingThings dog=new Dog(new LandBreathe());
        dog.breathe();
        LivingThings fish=new Dog(new WaterBreathe());
        fish.breathe();
    }    
}
