package Backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;


public class UserManager implements UserManagerInterface {

    private ArrayList<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void signup(String userID, String email, String username, String password, LocalDate dateOfBirth) {
        if (!findUserByID(userID)) {
            User u = new User(userID, email, username, dateOfBirth, password);
            users.add(u);
            u.setStatus("Online");
        }
    }

    @Override
    public void login(String email, String password) {
        for (User u : users) {
            if (email.equals(u.getEmail()) && password.equals(u.getPassword())) {
                u.setStatus("Online");
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
