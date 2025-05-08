public class App {
    public static void main(String[] args) throws Exception {
        Vehicle vh=new SportsVehicle();
        Vehicle vh1=new NormalVehicle();
        vh.drive();
        vh1.drive();
    }
}
