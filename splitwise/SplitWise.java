package splitwise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import splitwise.entity.Expense;
import splitwise.entity.Group;
import splitwise.entity.Split;
import splitwise.entity.SplitBuilder;
import splitwise.entity.Transaction;
import splitwise.entity.User;
import splitwise.strategy.SplitStrategy;

public class SplitWise {
    Map<String,User>userMap; // id to user
    Map<String,Group>groupMap;
    SplitWise(){
        userMap=new HashMap<>();
        groupMap=new HashMap<>();
    }
    public void createUser(String name,String contact,String userId){
        User user=new User(name, contact,userId);
        userMap.put(userId, user);
    }
    public User getUser(String userId){
        return userMap.get(userId);
    }
    public void createGroup(String groupId,String groupName,List<String>userContacts){
        List<User>users=new ArrayList<>();
        for(String s:userContacts){
            User user=userMap.get(s);
            if(user!=null)users.add(user);
        }
        Group group=new Group(groupId, groupName, users);
        groupMap.put(groupName, group);
    }
    public void createExpense(String expenseId,double amount,List<User>allUsers,User paidBy,SplitStrategy strategy){
        SplitBuilder builder=new SplitBuilder(amount, allUsers, paidBy, strategy);
        Expense expense=new Expense(expenseId, amount, paidBy, builder);
        for(Split split:expense.getSplit()){
            User participant=split.getUser();
            double money=split.getAmount();
            if(participant!=paidBy){
                participant.getSheet().adjustBalance(paidBy, -money);
                paidBy.getSheet().adjustBalance(participant, money);
            }
        }
    }
    public synchronized void settleUp(String payerId, String payeeId, double amount) {
        User payer = userMap.get(payerId);
        User payee = userMap.get(payeeId);
        System.out.println(payer.getName() + " is settling up " + amount + " with " + payee.getName());
        // Settlement is like a reverse expense. payer owes less to payee.

        payee.getSheet().adjustBalance(payer, -amount);
        payer.getSheet().adjustBalance(payee, amount);
    }    
    public void printBalanceSheet(String userId){
        User user=userMap.get(userId);
        user.getSheet().showBalances();
    }



    // TODO

    // public List<Transaction> simplifyGroupDebts(String groupId) {
    //     Group group = groups.get(groupId);
    //     if (group == null) throw new IllegalArgumentException("Group not found");

    //     // Calculate net balance for each member within the group context
    //     Map<User, Double> netBalances = new HashMap<>();
    //     for (User member : group.getMembers()) {
    //         double balance = 0;
    //         for(Map.Entry<User, Double> entry : member.getBalanceSheet().getBalances().entrySet()) {
    //             // Consider only balances with other group members
    //             if (group.getMembers().contains(entry.getKey())) {
    //                 balance += entry.getValue();
    //             }
    //         }
    //         netBalances.put(member, balance);
    //     }

    //     // Separate into creditors and debtors
    //     List<Map.Entry<User, Double>> creditors = netBalances.entrySet().stream()
    //             .filter(e -> e.getValue() > 0).collect(Collectors.toList());
    //     List<Map.Entry<User, Double>> debtors = netBalances.entrySet().stream()
    //             .filter(e -> e.getValue() < 0).collect(Collectors.toList());

    //     creditors.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    //     debtors.sort(Map.Entry.comparingByValue());

    //     List<Transaction> transactions = new ArrayList<>();
    //     int i = 0, j = 0;
    //     while (i < creditors.size() && j < debtors.size()) {
    //         Map.Entry<User, Double> creditor = creditors.get(i);
    //         Map.Entry<User, Double> debtor = debtors.get(j);

    //         double amountToSettle = Math.min(creditor.getValue(), -debtor.getValue());
    //         transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), amountToSettle));

    //         creditor.setValue(creditor.getValue() - amountToSettle);
    //         debtor.setValue(debtor.getValue() + amountToSettle);

    //         if (Math.abs(creditor.getValue()) < 0.01) i++;
    //         if (Math.abs(debtor.getValue()) < 0.01) j++;
    //     }
    //     return transactions;
    // }
}
