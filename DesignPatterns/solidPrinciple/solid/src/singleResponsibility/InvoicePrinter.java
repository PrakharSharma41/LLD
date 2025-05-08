package DesignPatterns.solidPrinciple.solid.src.singleResponsibility;

public class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }
    public void print(){}
    
}
