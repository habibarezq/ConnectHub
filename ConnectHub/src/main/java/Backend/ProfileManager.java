package Backend;

import Backend.FileManagers.ProfileFileManager;
import java.util.*;

public class ProfileManager {

    private final String userId;
    private static ProfileManager instance;
    private ArrayList<UserProfile> profiles;

    private boolean profilesLoaded = false;

    private ProfileManager(String userId) {
        this.userId = userId;
        this.profiles = null;
    }

    public static synchronized ProfileManager getInstance(String userId) {
        if (instance != null && !instance.userId.equals(userId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new ProfileManager(userId);
        }
        return instance;
    }

    public ArrayList<UserProfile> getProfiles() {
        if (!profilesLoaded) {
            loadProfiles();
        }
        return new ArrayList<>(profiles); // return a copy to protect internal data
    }

    private void loadProfiles() {
        if (!profilesLoaded) { // Ensure loading happens only once
            this.profiles = new ArrayList<>();
            ArrayList<UserProfile> allProfiles = ProfileFileManager.getInstance().getProfiles();
            for (UserProfile profile : allProfiles) {

                this.profiles.add(profile);
            }
        }
        profilesLoaded = true;
    }
    
     public void addProfile(UserProfile profile) {
     
            if (profiles == null) { // first profile to be created 
                profiles = new ArrayList<>();
            }
            profiles.add(profile); //Adds new profile to ArrayList of System Profiles
            ProfileFileManager.getInstance().getProfiles().add(profile); //Adds new Profile to ArrayList of AllProfiles
            ProfileFileManager.getInstance().saveToFile( ProfileFileManager.getInstance().getProfiles()); //Save to File
    }
  
}
