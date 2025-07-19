import java.math.BigDecimal;

public class BankAccount extends PaymentMethod {
    private final String accountNumber;
    private final String routingNumber;

    public BankAccount(String id, String accountNumber, String routingNumber) {
        super(id);
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        // Process bank account payment
        return true;
    }
}
