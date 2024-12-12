package Backend;

import Backend.UserManagement.User;
import Backend.FileManagers.ProfileFileManager;
import Backend.FileManagers.UserFileManager;
import Interfaces.*;

import java.util.ArrayList;

public class UserProfile implements UserProfileInterface {

    private String userId;
    private String profilePic;
    private String coverPic;
    private String bio;
    //private ProfileFileManager profileFileManager;

    public UserProfile(String userId, String profilePic, String coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
       // this.profileFileManager = ProfileFileManager.getInstance();
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
    @Override
    public void changeProfilePic(String profile) {
        this.profilePic = profile;
       ProfileFileManager.getInstance().saveToFile(ProfileFileManager.getInstance().getProfiles());
    }

    @Override
    public void changeCoverPic(String cover) {
        this.coverPic = cover;
       ProfileFileManager.getInstance().saveToFile(ProfileFileManager.getInstance().getProfiles());
    }

    @Override
    public void updateBio(String bio) {
        this.bio = bio;
       ProfileFileManager.getInstance().saveToFile(ProfileFileManager.getInstance().getProfiles());
    }

    @Override
    public void updatePassword(String password) {
        User user = UserFileManager.getInstance().findUserByID(userId);
        String hashedPass = Password.hashPassword(password);
        user.setPassword(hashedPass);
        UserFileManager.getInstance().saveToFile(UserFileManager.getInstance().getUsers());
    }
}
