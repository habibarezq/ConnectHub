package Backend;

import Interfaces.profileManager;
import java.awt.Image;

public class profile implements profileManager {

    UserManager manager = new UserManager();

    //method to update the profile photo
    @Override
    public boolean changeProfilePic(String userId, Image profile) {
        User user = manager.findUser(userId);
        return true;
    }

    //method to update the cover photo
    @Override
    public boolean changeCoverPic(String userId, Image cover) {
        User user = manager.findUser(userId);
        return true;
    }

    // method to enable the user to update the current bio
    @Override
    public boolean updateBio(String userId, String bio) {
        User user = manager.findUser(userId);
        //update bio
        return true;
    }

    // method to enable the user to update the current password
    @Override
    public boolean updatePassword(String userId, String password) {
        User user = manager.findUser(userId);
        String hashedPass = Password.hashPassword(password);

        //confirm updating passwords only if it does not match the old one
        if (Password.verifyPassword(user.getPassword(), hashedPass)) {
            return false;
        }
        user.setPassword(hashedPass);
        return true;
    }

}
