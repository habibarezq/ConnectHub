package Backend;

import Interfaces.*;
import java.awt.Image;

public class UserProfile implements ProfileManager {

    UserFileManager userManager = UserFileManager.getInstance();

    private String userId;
    private String profilePic;
    private String coverPic;
    private String bio;

    public UserProfile(String userId, String profilePic, String coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
    }

    // getters 
    public String getProfilePic() {
        return profilePic;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public String getBio() {
        return bio;
    }
    
    
    //method to update the profile photo
    @Override
    public void changeProfilePic(String profile) {
        this.profilePic = profile;

        //SaveToFile
    }

    //method to update the cover photo
    @Override
    public void changeCoverPic(String cover) {
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
