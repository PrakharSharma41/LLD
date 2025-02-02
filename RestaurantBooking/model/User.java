package RestaurantBooking.model;

public class User {
    String name;
    UserType userType;
    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }
    public String getName() {
        return name;
    }
    public UserType getUserType() {
        return userType;
    }
    
}
