package atm;

import java.util.HashMap;
import java.util.Map;

import atm.dispense.CashDispense;
import atm.entity.Account;
import atm.entity.Card;

public class BankService {
    Map<String,Account>accountMap;
    Map<String,Card>cardMap;
    
    public BankService() {
        accountMap=new HashMap<>();
        cardMap=new HashMap<>();

    }
    public void createAccount(String accountNumber, double balance){
        Account account=new Account(accountNumber, balance);
        accountMap.put(accountNumber, account);
    }
    public void createCard(String cardNumber,String pin,String cvv,String accountNumber){
        Account account=accountMap.get(accountNumber);
        Card card=new Card(cardNumber, pin, cvv,accountNumber);
        cardMap.put(cardNumber, card);
        account.addCard(card);
    }
    public boolean authenticate(Card card,String pin){
        return card.getPin().equals(pin);
    }
    public double checkBalance(Card card){
        String accountNumber=card.getAccountNumber();
        return accountMap.get(accountNumber).getBalance();
    }
    public void withdraw(Card card, int amount) {
        Account account=accountMap.get(card.getAccountNumber());
        account.withdraw(amount);
    }
	public void deposit(Card card,int amount) {
        Account account=accountMap.get(card.getAccountNumber());
        account.deposit(amount);

	}
    public Card getCard(String cardNumber) {
        return cardMap.get(cardNumber);
    }
}
