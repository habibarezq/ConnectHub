package Backend.FileManagers;

import Backend.GroupManagement.Group;
import Backend.GroupManagement.GroupRequest;
import Backend.GroupManagement.GroupUser;
import Backend.GroupManagement.MembershipManager;
import Backend.UserManagement.User;
import Backend.UserRequest;
import Interfaces.FileManager;
import Interfaces.FilePaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroupRequestsFileManager implements FileManager<GroupRequest> {

    private static GroupRequestsFileManager instance;
    private String FILE_PATH = FilePaths.GRP_REQUESTS_FILE_PATH;
    private ArrayList<GroupRequest> requests;

    private GroupRequestsFileManager() {
        this.requests = new ArrayList<>();
        readFromFile();
    }

    public static synchronized GroupRequestsFileManager getInstance() {
        if (instance == null) {
            instance = new GroupRequestsFileManager();
        }
        return instance;
    }

    public ArrayList<GroupRequest> getRequests() {
        return requests;
    }

    @Override
    public void readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            // If the file does not exist or is empty, initialize it with an empty array
            System.out.println("File not found or empty. Initializing with an empty posts array.");
            requests = new ArrayList<>();
            saveToFile(requests);
            return;
        }
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray requestsArray = new JSONArray(json);

            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJSON = requestsArray.getJSONObject(i);

                // Extract data from JSON
                String groupId = requestJSON.getString("groupId");
                String senderId = requestJSON.getString("senderId");
                String requestStat = requestJSON.getString("requestStat");
                String requestID = requestJSON.getString("requestID");

                Group group=GroupsFileManager.getInstance().getGroupById(groupId);
                GroupUser user=MembershipManager.getInstance(groupId).getGroupUserById(senderId);

                if (group != null && user != null) {

                    GroupRequest request = new GroupRequest(user,group);
                    request.setRequestStat(requestStat);

                    if (requestID != null) {
                        request.setRequestId(requestID); // Use ID from the file
                    } else {
                        request.setRequestId(java.util.UUID.randomUUID().toString()); // Generate a new ID if missing
                    }
                    System.out.println("Request" + request.getGroup().getName());
                    requests.add(request);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

    }

    @Override
    public void saveToFile(ArrayList<GroupRequest> data) {
 File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray requestsArray = new JSONArray();
        for (GroupRequest request : data) {
            JSONObject requestJSON = new JSONObject();
            requestJSON.put("groupId", request.getGroup().getGroupId());
            requestJSON.put("senderId", request.getUser().getGroupUserId());
            requestJSON.put("requestStat", request.getRequestStat());
            requestJSON.put("requestID", request.getRequestId()); // Ensure the ID is saved

            requestsArray.put(requestJSON);
        }

        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(requestsArray.toString(4)); // Pretty print
            writer.flush();
            writer.close();
            System.out.println("Requests saved successfully.");
        } catch (IOException ex) {
            System.out.println("Error saving requests: " + ex.getMessage());
        }
    }

    public GroupRequest findRequestByID(String requestID) {
        for (GroupRequest request : requests) {
            if (request.getRequestId().equals(requestID)) {
                return request;
            }
        }
        return null;
    }
}
