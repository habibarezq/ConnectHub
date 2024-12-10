package Backend.FileManagers;

import Backend.Request;
import Backend.User;
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

public class RequestsFileManager implements FileManager<Request> {

    private static RequestsFileManager instance;
    private String FILE_PATH = FilePaths.REQUESTS_FILE_PATH;
    private ArrayList<Request> requests;

    private RequestsFileManager() {
        this.requests = new ArrayList<>();
        readFromFile();
    }

    public static synchronized RequestsFileManager getInstance() {
        if (instance == null) {
            instance = new RequestsFileManager();
        }
        return instance;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    @Override
    public void readFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray requestsArray = new JSONArray(json);

            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJSON = requestsArray.getJSONObject(i);

                // Extract data from JSON
                String senderId = requestJSON.getString("senderId");
                String recipientId = requestJSON.getString("recipientId");
                String requestStat = requestJSON.getString("requestStat");
                String requestID = requestJSON.optString("requestID", null); // Use optString to handle missing fields

                // Fetch the User objects
                User sender = UserFileManager.getInstance().findUserByID(senderId);
                User recipient = UserFileManager.getInstance().findUserByID(recipientId);

                if (sender != null && recipient != null) {
                    // Create a new Request object
                    Request request = new Request(sender, recipient);
                    request.setRequestStat(requestStat);

                    // Handle requestID
                    if (requestID != null) {
                        request.setRequestID(requestID); // Use ID from the file
                    } else {
                        request.setRequestID(java.util.UUID.randomUUID().toString()); // Generate a new ID if missing
                    }

                    requests.add(request);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<Request> data) {
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray requestsArray = new JSONArray();
        for (Request request : data) {
            JSONObject requestJSON = new JSONObject();
            requestJSON.put("senderId", request.getSender().getUserID());
            requestJSON.put("recipientId", request.getRecipient().getUserID());
            requestJSON.put("requestStat", request.getRequestStat());
            requestJSON.put("requestID", request.getRequestID()); // Ensure the ID is saved

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

    public Request findRequestByID(String requestID) {
        for (Request request : requests) {
            if (request.getRequestID().equals(requestID)) {
                return request;
            }
        }
        return null;
    }
}
