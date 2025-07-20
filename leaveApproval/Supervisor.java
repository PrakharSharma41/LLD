package leaveApproval;

public class Supervisor extends Approver {
    @Override
    public void handleRequest(int days) {
        if (days <= 3) {
            System.out.println("Supervisor approved " + days + " days of leave ");
        } else if (nextApprover != null) {
            nextApprover.handleRequest(days);
        } else {
            System.out.println("Leave request for " + days + " days cannot be handled by Supervisor.");
        }
    }
    
}
