package adapterDesign;

public class WeightMachineAdapterImpl implements WeightMachineAdapter{
    WeightMachine weightMachine;
    @Override
    public int getWeightInKg() {
        int pound=weightMachine.getWeightInPounds();
        return (int)(pound/2.2046);
    }
    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }
    
}
