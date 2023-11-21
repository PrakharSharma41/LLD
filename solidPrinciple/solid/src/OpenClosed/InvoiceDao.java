package solidPrinciple.solid.src.OpenClosed;

interface InvoiceDao {
    public void save(Invoice invoice);
}

// unlike in solidResponsibility we should not add multiple methods in dao class