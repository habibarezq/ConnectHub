package Backend.FileManagers;

import Backend.GroupManagement.Group;
import Interfaces.FileManager;
import Interfaces.FilePaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupsFileManager implements FileManager<Group> {

    private static GroupsFileManager instance;
    private String FILE_PATH = FilePaths.GROUPS_FILE_PATH;
    private ArrayList<Group> groups;

    private GroupsFileManager() {
        this.groups = new ArrayList<>();
        readFromFile();
    }
    // Singleton pattern with userId specificity

    public static synchronized GroupsFileManager getInstance() {
        if (instance == null) {
            instance = new GroupsFileManager(); // Create a new instance for the specific userId
        }
        return instance;
    }

    public Group getGroupById(String groupId , String creatorId) {

        Group g = null;

        for (Group group : groups) {
            String currentGroupId = group.getGroupId();
            if (currentGroupId.equals(groupId)) {
                g = group;
                break;
            }
        }

        // If the group was not found, initialize with default values
        if (g == null) {
            g = new Group("newGroup", "", "", groupId, creatorId); // Default group with empty fields

        }
        return g;
    }

    @Override
    public void readFromFile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
                groups = new ArrayList<>();
                saveToFile(groups);
                return;
            } catch (IOException ex) {
                System.out.println("Error creating file: " + ex.getMessage());
                return;
            }
        }

        if (file.length() == 0) {
            groups = new ArrayList<>();
            saveToFile(groups);
            return;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray profilesArray = new JSONArray(json);
            this.groups.clear();

            for (int i = 0; i < profilesArray.length(); i++) {
                JSONObject profileJSON = profilesArray.getJSONObject(i);

                String creatorId = profileJSON.getString("creatorId");
                String groupId = profileJSON.getString("groupId");
                String groupName = profileJSON.getString("groupName");
                String description = profileJSON.getString("description");
                String photoPath = profileJSON.getString("photoPath");

                Group group = new Group(groupName, description, photoPath, groupId, creatorId);
                groups.add(group);
            }

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<Group> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray profilesArray = new JSONArray();

        for (Group group : data) {
            JSONObject profileJSON = new JSONObject();

            profileJSON.put("creatoId", group.getCreatorId());
            profileJSON.put("groupId", group.getGroupId());
            profileJSON.put("groupName", group.getName());
            profileJSON.put("description", group.getDescription());
            profileJSON.put("photoPath", group.getPhotoPath());

            profilesArray.put(profileJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(profilesArray.toString(5)); // Pretty print with indentation
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (IOException e) {
            System.out.println("Error saving profiles: " + e.getMessage());
        }
    }

}
