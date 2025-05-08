package DesignPatterns.facade;

public class Client {
    public void getEmployeeDetails(){
        EmployeeFacade employeeFacade=new EmployeeFacade();
        employeeFacade.getEmployeeDetails(121);
    }
}
