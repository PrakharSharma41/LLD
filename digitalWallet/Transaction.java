import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public class Transaction {
    private final Account fromAccount;
    private final Account toAccount;
    private final String id;
    private final LocalDateTime time;
    private final BigDecimal amount;
    public Transaction(String id, Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        this.id = id;
        this.fromAccount = sourceAccount;
        this.toAccount = destinationAccount;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }
    public Account getFromAccount() {
        return fromAccount;
    }
    public Account getToAccount() {
        return toAccount;
    }
    public String getId() {
        return id;
    }
    public LocalDateTime getTimestamp() {
        return time;
    }
    public BigDecimal  getAmount() {
        return amount;
    }   
}
