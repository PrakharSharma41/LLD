package solidPrinciple.solid.src.DependencyInversion;

public class MacBook {
    private final WiredKeyboard keyboard;
    private final BlueToothMouse mouse;

    // here we should create Keyboard and Mouse interface and create 
    // classes implementing this interface and create variables for 
    // those interfaces rather than concrete classes
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
    
}
