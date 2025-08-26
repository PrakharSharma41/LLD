
public abstract class OncallHandler {
    String name;
    OncallHandler nextHandler;
    boolean isAvailable;
    OncallHandler(String name,OncallHandler nextEmployee){
        this.name=name;
        this.nextHandler=nextEmployee;
        isAvailable=true;
    }
    public void handleOncall(Oncall oncall) {
        if (isAvailable) {
            doHandle(oncall);  // use if we need employee, manager or director some extra functionality
            System.out.println(this + " handled " + oncall);
            oncall.markHandled();
        } else if (nextHandler != null) {
            nextHandler.handleOncall(oncall);
        } else {
            System.out.println("No handler available for " + oncall);
        }
    }
    protected abstract void doHandle(Oncall oncall); 
    boolean isAvailable(){
        return isAvailable;
    }
    void setUnavailable(){
        isAvailable=false;
    }
    @Override
    public String toString() {
        return "OncallHandler [name=" + name + ", isAvailable=" + isAvailable + "]";
    }
}
