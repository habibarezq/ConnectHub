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
    private String userId;
    private UserProfile userProfile;

    // Private constructor, accepts userId to load the specific user profile
    private ProfileFileManager(String userId) {
        this.userId = userId;
        this.profiles = new ArrayList<>();
        this.userProfile = null;
        readFromFile();
    }

    // Singleton pattern with userId specificity
    public static synchronized ProfileFileManager getInstance(String userId) {
        if (instance == null || !instance.userId.equals(userId)) {
            instance = new ProfileFileManager(userId); // Create a new instance for the specific userId
        }
        return instance;
    }

    // Method to get the profile of the user
    public UserProfile getUserProfile() {
        if (userProfile == null) {
            readFromFile(); // Load the profile if not already loaded
        }
        return userProfile;
    }

    // Method to get all profiles
    public ArrayList<UserProfile> getProfiles() {
        if (profiles.isEmpty()) {
            readFromFile(); // If profiles are empty, load from file
        }
        return profiles;
    }

    @Override
    public void readFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray profilesArray = new JSONArray(json); // Parse the JSON array

            for (int i = 0; i < profilesArray.length(); i++) {
                JSONObject profileJSON = profilesArray.getJSONObject(i);
                String currentUserId = profileJSON.getString("userId");

                // Only load the profile if the userId matches
                if (currentUserId.equals(userId)) {
                    String bio = profileJSON.getString("Bio");
                    String profilePath = profileJSON.getString("ProfilePhotoPath");
                    String coverPath = profileJSON.getString("CoverPhotoPath");

                    // Create the UserProfile object for the specific user
                    userProfile = new UserProfile(userId, profilePath, coverPath, bio);
                    break; // Exit the loop as the profile has been found
                }
            }
        } catch (IOException | JSONException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

        // If the profile was not found, initialize with default values
        if (userProfile == null) {
            userProfile = new UserProfile(userId, "", "", ""); // Default profile with empty fields
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
