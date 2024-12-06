package Backend;

import Interfaces.*;
import java.awt.Image;
import java.util.ArrayList;

public class UserProfile implements ProfileManager {

    UserFileManager userManager = UserFileManager.getInstance();
    ProfileFileManager profileManager;
    private String userId;
    private String profilePic;
    private String coverPic;
    private String bio;
    private ArrayList<UserProfile> profiles;

    public UserProfile(String userId, String profilePic, String coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
        this.profileManager = ProfileFileManager.getInstance(userId);
        profiles = profileManager.getProfiles();
    }

    // getters 
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

    //method to update the profile photo
    @Override
    public void changeProfilePic(String profile) {
        this.profilePic = profile;
        profileManager.saveToFile(profiles);
    }

    //method to update the cover photo
    @Override
    public void changeCoverPic(String cover) {
        this.coverPic = cover;

        profileManager.saveToFile(profiles);
    }

    // method to enable the user to update the current bio
    @Override
    public void updateBio(String bio) {
        this.bio = bio;

        //SaveToFile
        profileManager.saveToFile(profiles);
    }

    // method to enable the user to update the current password
    @Override
    public void updatePassword(String password) {

        User user = userManager.findUserByID(userId);
        String hashedPass = Password.hashPassword(password);

        user.setPassword(hashedPass);

        userManager.saveToFile(UserFileManager.getInstance().getUsers());

    }
}
