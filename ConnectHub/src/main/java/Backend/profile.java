package Backend;

import Interfaces.*;
import java.awt.Image;

public class Profile implements ProfileManager {

    UserFileManager userManager = UserFileManager.getInstance();

    private String userId;
    private Image profilePic;
    private Image coverPic;
    private String bio;

    public Profile(String userId, Image profilePic, Image coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
    }
    
    
    //method to update the profile photo
    @Override
    public void changeProfilePic(Image profile) {
        this.profilePic = profile;

        //SaveToFile
    }

    //method to update the cover photo
    @Override
    public void changeCoverPic(Image cover) {
        this.coverPic = cover;

        //SaveToFile
    }

    // method to enable the user to update the current bio
    @Override
    public void updateBio(String bio) {
        this.bio = bio;

        //SaveToFile
    }

    // method to enable the user to update the current password
    @Override
    public boolean updatePassword(String userId, String password) {

        User user = userManager.findUserByID(userId);
        String hashedPass = Password.hashPassword(password);

        //confirm updating passwords only if it does not match the old one
        if (Password.verifyPassword(user.getPassword(), hashedPass)) {
            return false;
        }
        user.setPassword(hashedPass);

        // updating the users.txt file
        userManager.saveToFile(UserFileManager.getInstance().getUsers(), FilePaths.USERS_FILE_PATH);

        return true;
    }

}
