package DesignPatterns.compositeDesign.Calculator;

public class Main {
    public static void main(String[] args) {
        // implementing 2*(1+7)
        CustomArithmeticExpression leftArithmeticExpression=new Number(2);
        CustomArithmeticExpression rightArithmeticExpression=new Expression(new Number(1), new Number(7), Operation.ADDITION);

        CustomArithmeticExpression expression=new Expression(leftArithmeticExpression,rightArithmeticExpression,Operation.MULTIPLICATION);
        System.out.println(expression.evaluate());
    }
}
