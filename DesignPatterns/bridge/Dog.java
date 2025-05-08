package DesignPatterns.bridge;

public class Dog extends LivingThings{
    @Override
    void breathe() {
        super.breatheImplementor.breatheProcess();
    }

    public Dog(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
    }
    
}
