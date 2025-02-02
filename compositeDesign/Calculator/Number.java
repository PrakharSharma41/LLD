package compositeDesign.Calculator;

public class Number implements CustomArithmeticExpression{

    public int value;
    
    public Number(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
    
}

