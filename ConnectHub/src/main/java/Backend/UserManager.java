package Backend;

import Backend.FileManagers.ProfileFileManager;
import Backend.FileManagers.UserFileManager;
import Interfaces.UserManagerInterface;
import static Backend.Password.*;
//import static Backend.UserFileManager.readUsers;
import Interfaces.FilePaths;
import Validation.UserValidation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager implements UserManagerInterface {

    private ArrayList<User> users;

    public UserManager() {
        this.users = UserFileManager.getInstance().getUsers();
    }

    @Override
    public User signup(String email, String username, LocalDate dateOfBirth, String password) {
        User u = new User(UUID.randomUUID().toString(), email, username, dateOfBirth, hashPassword(password));
        users.add(u);
        UserProfile p = new UserProfile(u.getUserID(),"","","");
        ProfileFileManager.getInstance().getProfiles().add(p);
        u.setStatus(true);
        
        UserFileManager.getInstance().saveToFile(users); // Save the updated list
        ProfileFileManager.getInstance().saveToFile(ProfileFileManager.getInstance().getProfiles());
        return u;
    }

    @Override
    public User login(String email, String password) {
        for (User u : users) {
            if (email.equals(u.getEmail()) && verifyPassword(password, u.getPassword())) {
                u.setStatus(true);
                UserFileManager.getInstance().saveToFile(users); // Save the updated list
                return u;
            }
        }
        return null;
    }

    public void logout(String userId) {
        User user = UserFileManager.getInstance().findUserByID(userId);
        if (user != null) {
            user.setStatus(false);
            UserFileManager.getInstance().saveToFile(users);
        }
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
