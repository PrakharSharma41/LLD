package repository;

import java.util.HashMap;
import java.util.Map;

import models.Task;
import models.User;

public class UserRepository {
    Map<String,User>userMap;
    public UserRepository() {
        userMap=new HashMap<>();
    }
    public User createUser(String name){
        User user=new User(name);
        userMap.put(name,user);        
        return user;
    }
    public User getUser(String name){
        if(userMap.containsKey(name)){
            return userMap.get(name);
        }else{
            // System.out.println("user is not present");
            return null;
        }
    }
}
