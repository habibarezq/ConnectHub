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
    public Group getGroupById(String groupId) {

        Group g = null;

        for (Group group : groups) {
            String currentGroupId = group.getGroupId();
            if (currentGroupId.equals(groupId)) {
                g = group;
                break;
            }
        }
        return g;
    }
    
    public ArrayList<Group> getGroups(){
        return groups;
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
            JSONArray groupsArray = new JSONArray(json);
            this.groups.clear();

            for (int i = 0; i < groupsArray.length(); i++) {
                JSONObject groupJSON = groupsArray.getJSONObject(i);

                String creatorId = groupJSON.getString("creatorId");
                String groupId = groupJSON.getString("groupId");
                String groupName = groupJSON.getString("groupName");
                String description = groupJSON.getString("description");
                String photoPath = groupJSON.getString("photoPath");

                 
                Group group = new Group(groupName, description, photoPath, creatorId);
                if (groupId != null) {
                        group.setGroupId(groupId); // Use ID from the file
                    } else {
                        group.setGroupId(java.util.UUID.randomUUID().toString()); // Generate a new ID if missing
                    }
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

        JSONArray groupsArray = new JSONArray();

        for (Group group : data) {
            JSONObject groupJSON = new JSONObject();

            groupJSON.put("creatorId", group.getCreatorId());
            groupJSON.put("groupId", group.getGroupId());
            groupJSON.put("groupName", group.getName());
            groupJSON.put("description", group.getDescription());
            groupJSON.put("photoPath", group.getPhotoPath());

            groupsArray.put(groupJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(groupsArray.toString(5)); // Pretty print with indentation
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (IOException e) {
            System.out.println("Error saving profiles: " + e.getMessage());
        }
    }

      public Group getGroupByName(String selectedString) {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(selectedString)) {
                return group;
            }
        }
        return null;
    }
}
