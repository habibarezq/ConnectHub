package Backend;

import static Backend.Password.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager implements UserManagerInterface {

    private ArrayList<User> users;

    public UserManager() {
        this.users = UserFileManager.readUsers();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void signup(String email, String username, String password, LocalDate dateOfBirth) {
        User u = new User(UUID.randomUUID().toString(), email, username, dateOfBirth, hashPassword(password));
        users.add(u);
        u.setStatus(true);
    }

    @Override
    public void login(String email, String password) {
        for (User u : users) {
            if (email.equals(u.getEmail()) && verifyPassword(password, u.getPassword())) {
                u.setStatus(true);
            }
        }
    }

    public boolean findUserByID(String userID) {
        for (User u : users) {
            if (userID.equals(u.getUserID())) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String userID) {
        for (User u : users) {
            if (userID.equals(u.getUserID())) {
                return u;
            }
        }
        return null;
    }
}
