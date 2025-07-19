
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DigitalWallet {
    private final Map<String, User> users;
    private final Map<String, Account> accounts;

    private DigitalWallet() {
        users = new ConcurrentHashMap<>();
        accounts = new ConcurrentHashMap<>();
    }

    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void createAccount(Account account) {
        accounts.put(account.getId(), account);
        account.getUser().addAccount(account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addPaymentMethod(Account account,PaymentMethod paymentMethod) {
        account.addPaymentMethod(paymentMethod);
    }

    public void transferFunds(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        // using lock like this can prevent deadlocks
        // if multiple threads tries to acquire A->B or B->A both will first try to acquire smaller id locks
        Account firstLock = sourceAccount.getId().compareTo(destinationAccount.getId()) < 0 ? sourceAccount : destinationAccount;
        Account secondLock = sourceAccount.getId().compareTo(destinationAccount.getId()) < 0 ? destinationAccount : sourceAccount;
    
        synchronized (firstLock) {
            synchronized (secondLock) {
                if (sourceAccount.getBalance().compareTo(amount) < 0) { 
                    throw new IllegalArgumentException("Insufficient funds");
                }
    
                sourceAccount.withdraw(amount);
                destinationAccount.deposit(amount);
    
                String transactionId = generateTransactionId();
                Transaction transaction = new Transaction(transactionId, sourceAccount, destinationAccount, amount);
                sourceAccount.addTransaction(transaction);
                destinationAccount.addTransaction(transaction);
            }
        }
    }
    public void recharge(Account account, String paymentMethodId, BigDecimal amount) {
        PaymentMethod paymentMethod = account.getPaymentMethod(paymentMethodId);
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method not found.");
        }
    
        boolean success = paymentMethod.processPayment(amount);
        if (success) {
            account.deposit(amount);
            String txnId = generateTransactionId();
            Transaction txn = new Transaction(txnId, null, account, amount); // From external source
            account.addTransaction(txn);
            System.out.println("Recharge successful: " + amount);
        } else {
            System.out.println("Recharge failed for amount: " + amount);
        }
    }
    public void withdrawToBank(Account account, String bankPaymentMethodId, BigDecimal amount) {
        PaymentMethod method = account.getPaymentMethod(bankPaymentMethodId);
        if (!(method instanceof BankAccount)) {
            throw new IllegalArgumentException("Invalid bank account for withdrawal.");
        }
    
        account.withdraw(amount);
        // In real systems, you'd invoke `BankAccount.depositToExternalBank()` or similar.
        String txnId = generateTransactionId();
        Transaction txn = new Transaction(txnId, account, null, amount); // To external destination
        account.addTransaction(txn);
        System.out.println("Withdrawn to bank: " + amount);
    }    
    public List<Transaction> getTransactionHistory(Account account) {
        return account.getTransactions();
    }

    private String generateTransactionId() {
        return "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}