package atm.entity;

import java.util.HashMap;
import java.util.Map;

public class Account {
    String accountNumber;
    Map<String,Card>cards;
    double balance;
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        cards=new HashMap<>();
    }
    public void deposit(double amount){
        balance+=amount;
    }
    public void withdraw(double amount){
        balance-=amount;
    }
    public void addCard(Card card){
        cards.put(card.getCardNumber(), card);
    }    
    public double getBalance(){
        return balance;
    }
}
