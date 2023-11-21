package bridge;

public class Fish extends LivingThings{

    public Fish(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
        //TODO Auto-generated constructor stub
    }

    @Override
    void breathe() {
        super.breatheImplementor.breatheProcess();
    }
    
}
