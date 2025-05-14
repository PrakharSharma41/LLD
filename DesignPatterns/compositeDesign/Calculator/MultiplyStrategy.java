public class MultiplyStrategy implements ArithmeticOperationStrategy{

    @Override
    public int apply(int left, int right) {
        return left*right;
    }
    
}
