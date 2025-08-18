package atm.state;

import atm.ATM;
import atm.OperationType;
import atm.entity.Card;

public class IdleState implements ATMState{
    @Override
    public void insertCard(ATM atm, String cardNumber) {
        System.out.println("\nCard has been inserted.");
        Card card = atm.getCard(cardNumber);

        if (card == null) {
            ejectCard(atm);
        } else {
            atm.setCurrentCard(card);
            atm.changeState(new HasCardState());
        }
    }

    @Override
    public void enterPin(ATM atmSystem, String pin) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void selectOperation(ATM atmSystem, OperationType op, int... args) {
        System.out.println("Error: Please insert a card first.");
    }

    @Override
    public void ejectCard(ATM atmSystem) {
        System.out.println("Error: Card not found.");
        atmSystem.setCurrentCard(null);
    }     
}
