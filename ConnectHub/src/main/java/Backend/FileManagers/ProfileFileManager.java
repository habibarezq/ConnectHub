package Backend.FileManagers;

import Backend.UserProfile;
import Interfaces.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.*;

public class ProfileFileManager implements FileManager<UserProfile> {

    private static ProfileFileManager instance;
    private String FILE_PATH = FilePaths.PROFILES_FILE_PATH;
    private ArrayList<UserProfile> profiles;

    // Private constructor, accepts userId to load the specific user profile
    private ProfileFileManager() {
        this.profiles = new ArrayList<>();
        readFromFile();
    }

    // Singleton pattern with userId specificity
    public static synchronized ProfileFileManager getInstance() {
        if (instance == null) {
            instance = new ProfileFileManager(); // Create a new instance for the specific userId
        }
        return instance;
    }

    // Method to get the profile of the user
    public UserProfile getUserProfileById(String userId) {
        
        UserProfile p = null;
        
        for (UserProfile profile : profiles) {
            String currentUserId = profile.getUserId();
            if (currentUserId.equals(userId)) {
                p = profile;
                break;
            }
        }
        
        // If the profile was not found, initialize with default values
        if (p == null) {
            p = new UserProfile(userId, "", "", ""); // Default profile with empty fields
        }
        return p;
    }

    // Method to get all profiles //ERROORRRR
    public ArrayList<UserProfile> getProfiles() {
        if (profiles.isEmpty()) {
            readFromFile(); // If profiles are empty, load from file
        }
        return profiles;
    }
@Override
public void readFromFile() {
    File file = new File(FILE_PATH);

    if (!file.exists()) {
        try {
            file.createNewFile();
            profiles = new ArrayList<>();
            saveToFile(profiles);
            return;
        } catch (IOException ex) {
            System.out.println("Error creating file: " + ex.getMessage());
            return;
        }
    }

    if (file.length() == 0) {
        profiles = new ArrayList<>();
        saveToFile(profiles);
        return;
    }

    try {
        String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        JSONArray profilesArray = new JSONArray(json);
        this.profiles.clear();

        for (int i = 0; i < profilesArray.length(); i++) {
            JSONObject profileJSON = profilesArray.getJSONObject(i);
            String currentUserId = profileJSON.getString("userId");
            //Replaces getString with optString, allowing missing keys to be safely handled with default values.
            String bio = profileJSON.getString("Bio");  
            String profilePath = profileJSON.optString("ProfilePhotoPath", "");
            String coverPath = profileJSON.optString("CoverPhotoPath", "");

            UserProfile userProfile = new UserProfile(currentUserId, profilePath, coverPath, bio);
            profiles.add(userProfile);
        }

    } catch (IOException ex) {
        System.out.println("Error reading file: " + ex.getMessage());
    } catch (JSONException ex) {
        System.out.println("Error parsing JSON: " + ex.getMessage());
    }
}

    @Override
    public void saveToFile(ArrayList<UserProfile> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray profilesArray = new JSONArray();

        for (UserProfile profile : data) {
            JSONObject profileJSON = new JSONObject();
            profileJSON.put("userId", profile.getUserId());
            profileJSON.put("Bio", profile.getBio());
            profileJSON.put("ProfilePhotoPath", profile.getProfilePic());
            profileJSON.put("CoverPhotoPath", profile.getCoverPic());

            profilesArray.put(profileJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(profilesArray.toString(4)); // Pretty print with indentation
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (IOException e) {
            System.out.println("Error saving profiles: " + e.getMessage());
        }
    }
}
