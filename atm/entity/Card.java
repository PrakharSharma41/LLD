package atm.entity;

public class Card {
    String cardNumber;
    String pin;
    String cvv;
    String accountNumber;
    @Override
    public String toString() {
        return "Card [cardNumber=" + cardNumber + ", pin=" + pin + ", cvv=" + cvv + ", accountNumber=" + accountNumber
                + "]";
    }
    public Card(String cardNumber, String pin, String cvv,String accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.cvv = cvv;
        this.accountNumber=accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getPin() {
        return pin;
    }
    public String getCvv() {
        return cvv;
    }
    
}
