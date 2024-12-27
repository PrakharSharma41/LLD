package commandDesign;

public class TurnACOffCommand implements ICommand{
    AirConditioner ac;
    @Override
    public void execute() {
        ac.turnOFFAc();
    }
    @Override
    public void undo() {
        ac.turnONAc();
    }        
}
