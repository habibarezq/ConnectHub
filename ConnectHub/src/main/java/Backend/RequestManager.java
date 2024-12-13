package Backend;

import Backend.FileManagers.RequestsFileManager;
import java.util.ArrayList;

public class RequestManager {

    private final String userId;
    private static RequestManager instance;
    private ArrayList<UserRequest> userRequests;
    private ArrayList<UserRequest> allRequests;

    private boolean requestsSentToUserLoaded = false;
    private boolean requestsLoaded = false;

    private RequestManager(String userId) {
        this.userId = userId;
        this.userRequests = new ArrayList<>(); //ArrayList of Requests where user is reciepent
        this.allRequests = new ArrayList<>(); //ArrayList of allRequests where user is presents
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

    public ArrayList<UserRequest> getallUserRequests() {
        loadRequests();
        return allRequests;
    }

    public ArrayList<UserRequest> getRequests() {
        loadRequests();
        loadRequestsSentToUser();
        return userRequests;
    }

    public void loadRequestsSentToUser() {
        if (!requestsSentToUserLoaded) {
            for (UserRequest request : RequestsFileManager.getInstance().getRequests()) {
                if (request.getRecipient().getUserID().equals(userId) && request.getRequestStat().equals("Pending")) {
                    this.userRequests.add(request);
                }
            }
        }
        requestsSentToUserLoaded = true;
    }

    public void loadRequests() {
        if (!requestsLoaded) {
            for (UserRequest request : RequestsFileManager.getInstance().getRequests()) {
                if (request.getRecipient().getUserID().equals(userId) || request.getSender().getUserID().equals(userId)) {
                    this.allRequests.add(request); // Add all requests regardless of status
                }
            }
        }
        requestsLoaded = true;
    }

    public boolean search(String userId) {
        ArrayList<UserRequest> userRequests = getallUserRequests();

        if (userRequests != null) {
            for (UserRequest request : userRequests) {
                if ((request.getRecipient().getUserID().equals(userId) || request.getSender().getUserID().equals(userId))
                        && (request.getRequestStat().equals("Pending") || request.getRequestStat().equals("Accepted"))) {
                    return true; // User is involved in a pending or accepted request
                }
            }
        }
        return false;
    }

    public void refreshRequests() {
        this.allRequests = null;
        this.userRequests = null;
    }
}
