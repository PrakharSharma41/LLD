package leaveApproval;

public class Manager extends Approver {
    @Override
    public void handleRequest(int days) {
        if (days <= 7) {
            System.out.println("Manager approved " + days + " days of leave ");
        } else if (nextApprover != null) {
            nextApprover.handleRequest(days);
        } else {
            System.out.println("Leave request for " + days + " days cannot be handled by Manager.");
        }
    }

}
