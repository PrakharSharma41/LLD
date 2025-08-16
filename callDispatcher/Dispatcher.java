import java.util.List;
import java.util.Map;

public class Dispatcher {
    Map<EmployeeType, List<Employee>> employeeMap;
    CallQueueManager queueManager;

    public void handleCall(Call call) {
        for (EmployeeType type : EmployeeType.values()) {
            for (Employee emp : employeeMap.get(type)) {
                if (emp.isAvailable()) {
                    emp.handleCall(call);
                    call.employee = emp;
                    call.status = CallStatus.ACCEPTED;
                    return;
                }
            }
        }

        // No one available, enqueue the call
        queueManager.addToQueue(call);
    }

    public void completeCall(Call call) {
        call.status = CallStatus.COMPLETED;
        call.employee.status = EmployeeStatus.AVAILABLE;
        checkQueueAndAssign();
    }

    public void checkQueueAndAssign() {
        Call nextCall = queueManager.getNextCall();
        if (nextCall != null) {
            handleCall(nextCall); // Try assigning again
        }
    }
}
