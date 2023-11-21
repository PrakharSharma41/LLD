package solidPrinciple.solid.src.OpenClosed;

import solidPrinciple.solid.src.singleResponsibility.Marker;

public class Invoice {
    private Marker marker;
    private int quantity;
    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }
}
