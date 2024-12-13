package Backend.FileManagers;

import Interfaces.FilePaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupMembershipFileManager {

    private static GroupMembershipFileManager instance;
    private final String FILE_PATH = FilePaths.GROUPSMEMBER_FILE_PATH;

    private final Map<String, JSONObject> groupsMembership;

    private GroupMembershipFileManager() {
        this.groupsMembership = new HashMap<>();
        readFromFile();
    }
    // Singleton pattern with userId specificity

    public static synchronized GroupMembershipFileManager getInstance() {
        if (instance == null) {
            instance = new GroupMembershipFileManager(); // Create a new instance for the specific userId
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
            JSONArray allMembersArray = new JSONArray(json);

            groupsMembership.clear();
            for (int i = 0; i < allMembersArray.length(); i++) {
                JSONObject memObjJSON = allMembersArray.getJSONObject(i);
                String groupId = memObjJSON.getString("groupId");
                groupsMembership.put(groupId, memObjJSON);
            }
        } catch (IOException | JSONException ex) {
            System.out.println("Error reading Membership file: " + ex.getMessage());
        }
    }

    public void saveToFile() {
        try {
            JSONArray allUsersFriendsArray = new JSONArray(groupsMembership.values());
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(allUsersFriendsArray.toString(4));
            file.flush();
            file.close();
            System.out.println("Friends data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving Membership file: " + e.getMessage());
        }
    }
    
        public JSONObject getUserData(String groupId) {
        return groupsMembership.get(groupId);
    }

    public void updateUserData(String groupId, JSONObject data) {
        //groupsMembership to update a specific user's data in memory and save it.
        groupsMembership.put(groupId, data); 
    }

}
