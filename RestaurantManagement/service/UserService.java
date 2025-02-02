package RestaurantManagement.service;

public interface UserService {
    void registerUser(String name, String gender, String phoneNumber, String pincode);

    boolean loginUser(String userId);    
}
