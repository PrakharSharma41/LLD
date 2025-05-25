
public abstract class Employee {
    String name;
    String mobile;
    EmployeeStatus status;

    boolean isAvailable() {
        return status == EmployeeStatus.AVAILABLE;
    }

    abstract void handleCall(Call call);
}
