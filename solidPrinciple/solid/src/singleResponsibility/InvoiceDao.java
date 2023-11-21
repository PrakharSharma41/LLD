package solidPrinciple.solid.src.singleResponsibility;

public class InvoiceDao {
    Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }
    public void saveToDB(){}
}
