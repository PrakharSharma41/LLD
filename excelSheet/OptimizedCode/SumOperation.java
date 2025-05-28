package OptimizedCode;

public class SumOperation implements OperationStrategy{
    @Override
    public int perform(Cell... cells) {
        int sum=0;
        for(Cell c:cells){
            sum+=(int)c.getValue();
        }
        return sum;
    }
    
}
