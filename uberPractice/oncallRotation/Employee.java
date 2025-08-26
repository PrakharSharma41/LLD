public class Employee extends OncallHandler{

    Employee(String name,OncallHandler nextEmployee) {
        super(name,nextEmployee);
    }
    @Override
    protected void doHandle(Oncall oncall) {
        System.out.println(this + " handled (basic) " + oncall);
    }
}
