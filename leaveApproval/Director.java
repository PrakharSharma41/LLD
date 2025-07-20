package leaveApproval;

public class Director extends Approver {
    @Override
    public void handleRequest(int days) {
        if (days <= 14) {
            System.out.println("Director approved " + days + " days of leave ");
        } else if (nextApprover != null) {
            nextApprover.handleRequest(days);
        } else {
            System.out.println("Leave request for " + days + " days cannot be handled by Director.");
        }
    }
    
}
