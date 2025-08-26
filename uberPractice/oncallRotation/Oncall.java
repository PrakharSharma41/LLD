public class Oncall{
    OncallHandler assignee;
    long creationTime;
    @Override
    public String toString() {
        return "Oncall [description=" + description + "]";
    }
    Status status;
    String description;
    Oncall(String description){
        creationTime=System.currentTimeMillis();
        this.description=description;
    }
    void assignEmployee(OncallHandler employee){
        this.assignee=employee;
    }
    void markHandled(){
        status=Status.HANDLED;
    }
}