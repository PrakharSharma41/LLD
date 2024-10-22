package Foodkart;

public class User {
    private String name;
    private String gender;
    private String phone;
    private String pincode;
     UserType userType;
    public String getName() {
        return name;
    }
    public User(String name, String gender, String phone, String pincode,UserType userType) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.pincode = pincode;
        this.userType=userType;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", gender=" + gender + ", phone=" + phone + ", pincode=" + pincode + ", userType="
                + userType + "]";
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public User() {
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
