package RestaurantManagement.model;



public class User {
    String name;
    String phoneNumber;
    String pincode;

    public User(String name, String phoneNumber, String pincode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}