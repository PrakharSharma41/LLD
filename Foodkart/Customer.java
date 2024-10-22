package Foodkart;

public class Customer extends User{

    public Customer(String name, String gender, String phone, String pincode) {
    super(name, gender, phone, pincode, UserType.CUSTOMER);
    }
    
}
