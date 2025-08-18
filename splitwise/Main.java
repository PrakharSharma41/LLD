package splitwise;

import java.util.List;

import splitwise.entity.User;
import splitwise.strategy.EqualSplitStrategy;

public class Main {
    public static void main(String[] args) {
        SplitWise splitWise=new SplitWise();
        splitWise.createUser("user1", "123456", "u1");
        splitWise.createUser("user2", "456765", "u2");

        List<User>users=List.of(splitWise.getUser("u1"),splitWise.getUser("u2"));
        splitWise.createExpense("e1", 100.12, users, splitWise.getUser("u1"), new EqualSplitStrategy());
        splitWise.settleUp("u2","u1",106);
        splitWise.printBalanceSheet("u1");
    }
}
