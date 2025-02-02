package RestaurantManagement.dao;

import java.util.HashMap;

import RestaurantManagement.model.User;

public class UserDao {
    private static UserDao userDaoInstance = null;
    private HashMap<String, User> userMap;
    private User currentLoginUser;

    private UserDao() {
        this.userMap = new HashMap<>();
        currentLoginUser = null;
    }

    public static UserDao getInstance() {
        if (userDaoInstance == null)
            userDaoInstance = new UserDao();

        return userDaoInstance;
    }

    public void addUser(User user) {
        if (userMap.containsKey(user.getPhoneNumber())) {
            // throw new UserAlreadyPresent("User already exists with same name " + user.getName());
        }
        userMap.put(user.getPhoneNumber(), user);
    }

    public User getUser(String userPhoneNumber) {
        if (!userMap.containsKey(userPhoneNumber)) {
            // throw new UserNotPresent("User not found");
        }
        return userMap.get(userPhoneNumber);
    }

    public User getCurrentLoginUser() {
        return currentLoginUser;
    }

    public void setCurrentLoginUser(User currentLoginUser) {
        this.currentLoginUser = currentLoginUser;
    }    
}
