package Backend.FileManagers;

import Backend.Request;
import Backend.UserManagement.User;
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
                String senderId = requestJSON.getString("senderId");
                String recipientId = requestJSON.getString("recipientId");
                String requestStat = requestJSON.getString("requestStat");
                String requestID = requestJSON.getString("requestID");

                User sender = UserFileManager.getInstance().findUserByID(senderId);
                User recipient = UserFileManager.getInstance().findUserByID(recipientId);

                if (sender != null && recipient != null) {

                    Request request = new Request(sender, recipient);
                    request.setRequestStat(requestStat);

                    if (requestID != null) {
                        request.setRequestID(requestID); // Use ID from the file
                    } else {
                        request.setRequestID(java.util.UUID.randomUUID().toString()); // Generate a new ID if missing
                    }
                    System.out.println("Request"+request.getRecipient().getUsername());
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
    
    public boolean searchForUser(String userId)
    {
        
        for(Request request: RequestsFileManager.getInstance().getRequests())
        {
            if(request.getRecipient().getUserID().equals(userId) || request.getSender().getUserID().equals(userId))
            {
                //if the user is a sender or reciever
                return true;
            }
        }
        return false;
    }
      public Request searchForRequestByIds(String senderId, String recipientId) {
    for (Request r : RequestsFileManager.getInstance().getRequests()) {
        if ((r.getRecipient().getUserID().equals(recipientId)) && (r.getSender().getUserID().equals(senderId))) {
            
            { System.out.println("Found !");
            return r; }
        }
    }
    return null; // Return null if no match is found
}


    public Request searchByRequestId(String requestId) {
        for (Request r : RequestsFileManager.getInstance().getRequests()) {
                return r;
            }
        return null;
    }
}
