package DesignPatterns.compositeDesign.Calculator;

public class Expression implements CustomArithmeticExpression{
    public CustomArithmeticExpression leftExpression;
    public CustomArithmeticExpression rightExpression;
    public ArithmeticOperationStrategy operation;
    
    public Expression(CustomArithmeticExpression leftExpression, CustomArithmeticExpression rightExpression,
    ArithmeticOperationStrategy operation) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    @Override
    public int evaluate() {
        return operation.apply(leftExpression.evaluate(),rightExpression.evaluate());
    }
    
}
