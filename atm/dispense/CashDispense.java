package atm.dispense;

public interface CashDispense {
    public void setNextDispenser(CashDispense cashDispense);
    public void dispense(int amount); 
    public boolean canDispense(int amount);
}
