package DesignPatterns.templateDesign;

public abstract class PaymentFlow {
    public abstract void validateRequest();
    public abstract void calculateFees();
    public abstract void debitAmount();
    public abstract void creditAmount();

    // below is the template method
    public final void sendMoney(){
        validateRequest();
        debitAmount();
        calculateFees();
        creditAmount();
    }
}
// we want all classes to call abpve 4 methods in the same order we defined in sendMoney method
// each child class can provide their own implementation