package Backend;

import static Backend.Password.*;
//import static Backend.UserFileManager.readUsers;
import Interfaces.FilePaths;
import Validation.UserValidation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager implements UserManagerInterface {

    private ArrayList<User> users;

    public UserManager(ArrayList<User> users) {
        this.users = users;
    }


    @Override
    public User signup(String email, String username, LocalDate dateOfBirth, String password) {
        if (UserValidation.isEmailTaken(email,users)) {
            throw new IllegalArgumentException("Email is already taken");
        }
        else if(UserValidation.isUsernameTaken(username, users))
        {
            throw new IllegalArgumentException("UserName is already taken");
        }
        User u = new User(UUID.randomUUID().toString(), email, username, dateOfBirth, hashPassword(password));
        users.add(u);
        u.setStatus(true);
        UserFileManager.getInstance().saveTOFile(users, FilePaths.USERS_FILE_PATH); // Save the updated list
        return u;
    }

    @Override
    public User login(String email, String password) {
        for (User u : users) {
            if (email.equals(u.getEmail()) && verifyPassword(password, u.getPassword())) {
                u.setStatus(true);
            }
        }
    return null;
    }
    
    public boolean loginValidation(String email, String password) {
        for (User u : users) {
            if (email.equals(u.getEmail()) && verifyPassword(password, u.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
