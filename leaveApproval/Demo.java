package leaveApproval;

public class Demo {
    public static void main(String[] args) {
        // Create the chain of approvers
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();

        // Set the chain of responsibility
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);

        // Process the leave requests
        supervisor.handleRequest(2);
        supervisor.handleRequest(5);
        supervisor.handleRequest(10);
        supervisor.handleRequest(15);
    }
}
