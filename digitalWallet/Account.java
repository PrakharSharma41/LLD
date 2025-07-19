import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class Account {
    private final String id;
    private final User user;
    private final String accountNumber;
    private BigDecimal balance;
    private final List<Transaction> transactions;
    private Map<String,PaymentMethod>paymentMethods;

    public Account(String id, User user, String accountNumber) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
        paymentMethods = new HashMap<>();
    }

    public synchronized void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance=balance.add(amount);
    }

    public synchronized void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (balance.compareTo(amount)< 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance = balance.subtract(amount);
    }

    public synchronized void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
        public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.put(paymentMethod.getId(), paymentMethod);
    }

    public PaymentMethod getPaymentMethod(String paymentMethodId) {
        return paymentMethods.get(paymentMethodId);
    }
}