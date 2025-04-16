package com.gym.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.gym.dao.UserDAO;
import com.gym.user.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean register(User user) {

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return userDAO.registerUser(user);
        }
    
    

public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);

        System.out.println("User email: " + email); 

        if (user != null) {
         System.out.println("User found in database: " + user); 
    
            System.out.println("Hashed password from database: " + user.getPassword());
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Password matches!"); 
            return user;
    } else {
        System.out.println("Password does not match."); 
        System.out.println("Entered password: " + password); 
    }
    System.out.println("User not found with email: " + email);
    System.out.println("Invalid email or password");

    return null;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }


    



}