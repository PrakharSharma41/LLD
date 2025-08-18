package atm.dispense;

public class Dispenser implements CashDispense{
    CashDispense nextCashDispense;
    int numberOfNotes;
    int noteValue;
    
    public Dispenser(int numberOfNotes, int noteValue) {
        this.numberOfNotes = numberOfNotes;
        this.noteValue = noteValue;
    }

    @Override
    public void setNextDispenser(CashDispense cashDispense) {
        nextCashDispense=cashDispense;
    }

    @Override
    public void dispense(int amount) {
        if(amount>=noteValue){
            int numToUse=amount/noteValue;
            System.out.println(numToUse+" with value "+noteValue+" dispensed");
            int left=amount-(numToUse*noteValue);
            if(left>0&&nextCashDispense!=null)nextCashDispense.dispense(left);
        }else if(nextCashDispense!=null)nextCashDispense.dispense(amount);
    }

    @Override
    public boolean canDispense(int amount) {
        if(amount<0)return false;
        int numToUse=amount/noteValue;
        if(numToUse>numberOfNotes)return false;
        int left=amount-(numToUse*noteValue);
        if(left==0)return true;
        if(nextCashDispense!=null)return nextCashDispense.canDispense(left);
        return false;
    }
    

    
}
