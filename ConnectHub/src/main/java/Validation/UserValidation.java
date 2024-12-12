package Validation;

import Backend.UserManagement.User;
import java.util.ArrayList;

public class UserValidation {

    public static boolean isEmailTaken(String email, ArrayList<User> users) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return true; // Email is already taken
            }
        }
        return false; // Email is not taken
    }
    
    public static boolean isUsernameTaken(String userName,ArrayList<User> users)
    {
        for (User u : users) {
            if (u.getUsername().equals(userName)) {
                return true; // Username is already taken
            }
        }
        return false; // Username is not taken
    }
}

