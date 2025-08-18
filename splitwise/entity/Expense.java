package splitwise.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Expense {
    String expenseId;
    double amount;
    List<Split>split;
    User paidBy;
    SplitBuilder builder;
    public Expense(String expenseId, double amount, User paidBy,SplitBuilder builder) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.builder=builder;
        this.split=builder.createSplits();
    }
    public String getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public List<Split> getSplit() {
        return split;
    }
    public void setSplit(List<Split> split) {
        this.split = split;
    }
    public User getPaidBy() {
        return paidBy;
    }
    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }
    
}
