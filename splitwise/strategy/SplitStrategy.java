package splitwise.strategy;

import java.util.List;

import splitwise.entity.Split;
import splitwise.entity.User;

public interface SplitStrategy {
    List<Split> calculateSplit(double amount, List<User>allUsers,User paidBy);
}
