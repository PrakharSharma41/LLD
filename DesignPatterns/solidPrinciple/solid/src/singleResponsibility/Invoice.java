package DesignPatterns.solidPrinciple.solid.src.singleResponsibility;

public class Invoice {
    private Marker marker;
    private int quantity;
    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // below three methods should have separate classes 
    // else on changing something in these methods we are changing Invoice class
    // public int calcculateTotal(){
    //     return 0;
    // }
    // public void printInvoice(){}
    // public void saveToDB(){}
}
