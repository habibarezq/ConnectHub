package Interfaces;

import Backend.UserManagement.User;
import java.time.LocalDate;

public interface UserManagerInterface {

    public User signup(String email, String username, LocalDate dateOfBirth,String password);
    public User login(String email,String password);
    
}
