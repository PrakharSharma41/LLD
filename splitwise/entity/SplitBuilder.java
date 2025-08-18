package splitwise.entity;

import java.util.List;

import splitwise.strategy.SplitStrategy;

public class SplitBuilder {
    double amount;
    List<User>allUsers;
    User paidBy;
    SplitStrategy strategy;

    public SplitBuilder(double amount,List<User>allUsers,User paidBy,SplitStrategy strategy) {
        this.amount=amount;
        this.allUsers=allUsers;
        this.paidBy=paidBy;
        this.strategy = strategy;
    }

    public SplitStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SplitStrategy strategy) {
        this.strategy = strategy;
    }
    public List<Split>createSplits(){
        return strategy.calculateSplit(amount, allUsers, paidBy);
    }
}
