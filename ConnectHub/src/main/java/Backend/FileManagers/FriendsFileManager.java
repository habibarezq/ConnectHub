package Backend.FileManagers;

import Backend.*;
import Interfaces.FilePaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendsFileManager {

    private static FriendsFileManager instance;
    private final String FILE_PATH = FilePaths.FRIENDS_FILE_PATH;
    private final Map<String, JSONObject> userFriendsData;

    private FriendsFileManager() {
        userFriendsData = new HashMap<>();
        readFromFile();
    }

    public static synchronized FriendsFileManager getInstance() {
        if (instance == null) {
            instance = new FriendsFileManager();
        }
        return instance;
    }

    public void readFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                return; // No data to load
            }

            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray allUsersFriendsArray = new JSONArray(json);

            userFriendsData.clear();
            for (int i = 0; i < allUsersFriendsArray.length(); i++) {
                JSONObject userFriendBlockedJSON = allUsersFriendsArray.getJSONObject(i);
                String userId = userFriendBlockedJSON.getString("userId");
                userFriendsData.put(userId, userFriendBlockedJSON);
            }
        } catch (IOException | JSONException ex) {
            System.out.println("Error reading friends file: " + ex.getMessage());
        }
    }

    public void saveToFile() {
        try {
            JSONArray allUsersFriendsArray = new JSONArray(userFriendsData.values());
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(allUsersFriendsArray.toString(4));
            file.flush();
            file.close();
            System.out.println("Friends data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving friends file: " + e.getMessage());
        }
    }

    public JSONObject getUserData(String userId) {
        return userFriendsData.get(userId);
    }

    public void updateUserData(String userId, JSONObject data) {
        //updateUserData to update a specific user's data in memory and save it.
        userFriendsData.put(userId, data); 
    }
}
