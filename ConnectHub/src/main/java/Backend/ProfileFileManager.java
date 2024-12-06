package Backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ProfileFileManager implements FileManager<UserProfile> {

    private static ProfileFileManager instance;
    private ArrayList<UserProfile> profiles;
    private String FILE_PATH = FilePaths.PROFILES_FILE_PATH;

    // private constructor to avoid instantiation
    private ProfileFileManager() {
        this.profiles = new ArrayList<>();
        readFromFile();
    }

    public static ProfileFileManager getInstance() {
        if (instance == null) {
            instance = new ProfileFileManager();
        }
        return instance;
    }

    public ArrayList<UserProfile> getProfiles() {
        if (profiles.isEmpty()) {
            readFromFile(); //EH EL LOGIC HENAA
        }
        return profiles;
    }


    @Override
    public void readFromFile() {
        if (!profiles.isEmpty()) {
            return; // To avoid reloading
        }
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray postsArray = new JSONArray(json); // Parse the JSON array

            this.profiles.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postJSON = postsArray.getJSONObject(i);
                String userId = postJSON.getString("userId");
                String bio = postJSON.getString("Bio");
                String profilePath = postJSON.getString("ProfilePhotoPath");
                String coverPath = postJSON.getString("CoverPhotoPath");

                profiles.add(new UserProfile(userId, profilePath, coverPath , bio));
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

        JSONArray postsArray = new JSONArray();
        for (UserProfile profile : data) {
            JSONObject postJSON = new JSONObject();
            postJSON.put("userId", profile.getUserId());
            postJSON.put("Bio", profile.getBio());
            postJSON.put("ProfilePhotoPath", profile.getProfilePic());
            postJSON.put("CoverPhotoPath", profile.getCoverPic());
          
            postsArray.put(postJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(postsArray.toString(4));
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving posts: " + e.getMessage());
        }
    }
}

