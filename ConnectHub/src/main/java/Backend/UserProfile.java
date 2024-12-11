package Backend;

import Backend.FileManagers.ProfileFileManager;
import Backend.FileManagers.UserFileManager;
import Interfaces.*;

import java.util.ArrayList;

public class UserProfile  {

    private String userId;
    private String profilePic;
    private String coverPic;
    private String bio;
    private ProfileFileManager profileManager;

    public UserProfile(String userId, String profilePic, String coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
        //this.profileManager = ProfileFileManager.getInstance(userId);
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public String getBio() {
        return bio;
    }

    // Methods to update profile data
    public void changeProfilePic(String profile) {
        this.profilePic = profile;
       // profileManager.saveToFile(profileManager.getProfiles());
    }


    public void changeCoverPic(String cover) {
        this.coverPic = cover;
       // profileManager.saveToFile(profileManager.getProfiles());
    }

    public void updateBio(String bio) {
        this.bio = bio;
       // profileManager.saveToFile(profileManager.getProfiles());
    }

    public void updatePassword(String password) {
        User user = UserFileManager.getInstance().findUserByID(userId);
        String hashedPass = Password.hashPassword(password);
        user.setPassword(hashedPass);
        UserFileManager.getInstance().saveToFile(UserFileManager.getInstance().getUsers());
    }
}
