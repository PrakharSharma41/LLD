package compositeDesign.Calculator;

public class Expression implements CustomArithmeticExpression{
    public CustomArithmeticExpression leftExpression;
    public CustomArithmeticExpression rightExpression;
    Operation operation;
    
    public Expression(CustomArithmeticExpression leftExpression, CustomArithmeticExpression rightExpression,
            Operation operation) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    @Override
    public int evaluate() {
        int ans=0;
        switch(operation){
            case ADDITION:
                ans=leftExpression.evaluate()+rightExpression.evaluate();
                // System.out.println("ans is "+ans);
                break;
            case MULTIPLICATION:
            // System.out.println("right is "+rightExpression.evaluate());
                ans=leftExpression.evaluate()*rightExpression.evaluate();
        }
        return ans;
    }
    
}
