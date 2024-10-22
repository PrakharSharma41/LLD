package Foodkart;

public class Owner extends User{

    public Owner(String name, String gender, String phone, String pincode) {
        super(name, gender, phone, pincode, UserType.OWNER);
    }
    
}
