public class AirCraft{
    String id;
    String model;
    int totalSeats;
    public AirCraft(String id, String model, int totalSeats) {
        this.id = id;
        this.model = model;
        this.totalSeats = totalSeats;
    }
    public String getTailNumber(){
        return id;
    }
}