package service;

import models.User;
import repository.UserRepository;

public class UserService {
    UserRepository userRepository;

    public UserService() {
        userRepository=new UserRepository();
    }
    public User createUser(String name){
        User user=userRepository.createUser(name);
        return user;
    }
    public User getUser(String name){
        return userRepository.getUser(name);
    }
}
