public class Main {
    public static void main(String[] args) {
        Oncall oncall=new Oncall("oncall1");
        OncallHandler director=new Director("director", null);
        OncallHandler manager=new Manager("manager",director);
        OncallHandler employee=new Employee("employee", manager);
        employee.setUnavailable();
        manager.setUnavailable();
        // director.setUnavailable();
        employee.handleOncall( oncall);
    }
}
