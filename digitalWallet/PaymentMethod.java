import java.math.BigDecimal;
import java.util.Currency;

public abstract class PaymentMethod {
    protected final String id;

    public PaymentMethod(String id) {
        this.id = id;
    }

    public abstract boolean processPayment(BigDecimal amount);

    public String getId() {
        return id;
    }
}
