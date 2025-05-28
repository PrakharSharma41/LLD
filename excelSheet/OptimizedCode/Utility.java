package OptimizedCode;

public class Utility {
    public static OperationStrategy getStrategy(CellFunction cellFunction){
        switch (cellFunction) {
            case CellFunction.SUM:
                return new SumOperation();        
            default:
                return null;
        }
    }
}
