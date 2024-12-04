package facade;

public class EmployeeFacade {
    EmployeeDao employeeDao;

    public EmployeeFacade() {
    }
    public void insert(){
        employeeDao.insert();
    }
    public Employee getEmployeeDetails(int id){
        return employeeDao.getEmployeeDetails(id);
    }
}
