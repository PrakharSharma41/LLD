package DesignPatterns.solidPrinciple.solid.src.DependencyInversion;

public class MacBook {
    private final WiredKeyboard keyboard;
    private final BlueToothMouse mouse;
    // Entities must depend on abstractions, not on concretions. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions.
    // here we should create Keyboard and Mouse interface and create 
    // classes implementing this interface and create variables for 
    // those interfaces rather than concrete classes
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
    
}
