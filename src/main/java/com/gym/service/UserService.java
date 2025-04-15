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

        System.out.println("User email: " + email); // Debugging

        if (user != null) {
         System.out.println("User found in database: " + user); // Debugging
    
            System.out.println("Hashed password from database: " + user.getPassword()); // Debugging
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Password matches!"); // Debugging
            return user;
    } else {
        System.out.println("Password does not match."); // Debugging
        System.out.println("Entered password: " + password); // Debugging
    }
    System.out.println("User not found with email: " + email); // Debugging
    System.out.println("Invalid email or password");

    return null;
    }









    //     BCrypt.checkpw(password, user.getPassword())) {
    //         return user;
    //     }
    // else {
    //     System.out.println("Invalid email or password");
    // }
    //     return null;
    // }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }


    



}