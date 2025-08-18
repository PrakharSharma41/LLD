package atm;

import atm.dispense.CashDispense;
import atm.dispense.Dispenser200;
import atm.dispense.Dispenser500;
import atm.entity.Card;
import atm.state.ATMState;
import atm.state.IdleState;

public class ATM {
    ATMState atmState;
    BankService bankService;
    Card currentCard;
    CashDispense cashDispense;
    ATM(BankService bankService){
        atmState=new IdleState();
        this.bankService=bankService;
        CashDispense c1 = new Dispenser200(10); // 10 x $100 notes
        CashDispense c2 = new Dispenser500(20); // 20 x $50 notes
        c1.setNextDispenser(c2);
        this.cashDispense=c1;
    }
    public Card getCard(String cardNumber){
        return bankService.getCard(cardNumber);
    }
    public void setCurrentCard(Card card){
        this.currentCard=card;
    }
    public void changeState(ATMState atmState){
        this.atmState=atmState;
    }
    public Card getCurrentCard(){
        return currentCard;
    }
    public boolean authenticate(String pin){
        return bankService.authenticate(currentCard, pin);
    }
    public double checkBalance(){
        return bankService.checkBalance(currentCard);
    }
    public BankService getBankService(){
        return bankService;
    }
    public void withdrawCash(int amount){
        bankService.withdraw(currentCard,amount);
        if(cashDispense.canDispense(amount)){
            cashDispense.dispense(amount);
        }
    }
    public void depositCash(int amount){
        bankService.deposit(currentCard,amount);
    }
    public void insertCard(String cardNumber) {
        atmState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        atmState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int... args) { atmState.selectOperation(this, op, args); }

}
