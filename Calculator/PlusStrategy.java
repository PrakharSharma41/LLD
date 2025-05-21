package DesignPatterns.compositeDesign.Calculator;

public class PlusStrategy implements ArithmeticOperationStrategy{

    @Override
    public int apply(int left, int right) {
        return left+right;
    }
    
}
