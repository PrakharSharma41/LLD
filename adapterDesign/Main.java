package adapterDesign;

public class Main {
    public static void main(String[] args) {
        WeightMachineAdapter adapter=new WeightMachineAdapterImpl(new WeightMachineImpl());
        adapter.getWeightInKg();
    }
}
