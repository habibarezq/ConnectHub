package Backend;

import Backend.FileManagers.RequestsFileManager;
import java.util.ArrayList;

public class RequestManager {

    private final String userId;
    private static RequestManager instance;
    private ArrayList<Request> userRequests;
    private boolean requestsLoaded = false;

    private RequestManager(String userId) {
        this.userId = userId;
        this.userRequests = null;
    }

    public static synchronized RequestManager getInstance(String userId) {
        if (instance != null && !instance.userId.equals(userId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new RequestManager(userId);
        }
        return instance;
    }

    public ArrayList<Request> getUserRequests() {
        loadRequestsSentToUser();
        return userRequests;
    }

    private void loadRequestsSentToUser() {
        if (!requestsLoaded) { // Ensure loading happens only once
            for (Request request : RequestsFileManager.getInstance().getRequests()) {
                if (request.getRecipient().getUserID().equals(userId)) {
                    this.userRequests.add(request);
                }
            }
        }
        requestsLoaded = true;
    }

}
