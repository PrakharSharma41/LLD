package atm;

public class Main {
    public static void main(String[] args) {
        BankService bankService=new BankService();
        ATM atm=new ATM(bankService);
        bankService.createAccount("a1", 5000.00);
        bankService.createCard("c1", "5000", "123", "a1");

        atm.insertCard("c1");
        atm.enterPin("5000");
        atm.selectOperation(OperationType.DEPOSIT_CASH, 500);

        atm.insertCard("c1");
        atm.enterPin("5000");
        atm.selectOperation(OperationType.CHECK_BALANCE);

        atm.insertCard("c1");
        atm.enterPin("5000");
        atm.selectOperation(OperationType.WITHDRAW_CASH,200 );

        atm.insertCard("c1");
        atm.enterPin("5000");
        atm.selectOperation(OperationType.CHECK_BALANCE);
        // System.out.println();;
    }
}
