package commandDesign;

public class TurnACOnCommand implements ICommand{

    AirConditioner ac;
    public TurnACOnCommand(AirConditioner ac) {
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.turnONAc();
    }
    @Override
    public void undo() {
        ac.turnOFFAc();
    }
    
}
