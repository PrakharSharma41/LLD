package splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import splitwise.entity.Split;
import splitwise.entity.User;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> calculateSplit(double amount, List<User> allUsers, User paidBy) {
        List<Split>splits=new ArrayList<>();
        double amountPerPerson=amount/allUsers.size();
        for(int i=0;i<allUsers.size();i++)splits.add(new Split(allUsers.get(i), amountPerPerson));
        return splits;
    }
    
}
