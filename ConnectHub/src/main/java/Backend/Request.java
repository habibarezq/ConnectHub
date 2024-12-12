package Backend;

import Backend.FileManagers.FriendsFileManager;
import Backend.FileManagers.RequestsFileManager;
import java.util.UUID;

public class Request {

    private User sender;
    private User recipient;
    private String requestStat;
    private String requestID;

    public Request(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.requestStat = "Pending";
        this.requestID = UUID.randomUUID().toString();

    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestStat() {
        return requestStat;
    }

    public void setRequestStat(String requestStat) {
        this.requestStat = requestStat;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void processFriendRequest() {
        System.out.println("Processing Friend Request ...");
        if (!recipient.getBlocked().contains(sender)) {
            requestStat = "Pending";

            //Save to File
            RequestsFileManager.getInstance().getRequests().add(this);
            RequestManager.getInstance(recipient.getUserID()).getallUserRequests().add(this);
            RequestManager.getInstance(sender.getUserID()).getallUserRequests().add(this);

            for (Request r : RequestsFileManager.getInstance().getRequests()) {
                System.out.println("Request " + r.getSender());
            }
            RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
            System.out.println("ID:" + this.requestID);
            System.out.println("Friend Request sent From " + sender.getUsername() + " --> " + recipient.getUsername());
        } else {
            System.out.println("Friend request already sent.");
        }

    }

    public void processAcceptFriendRequest() {
        Request request = RequestsFileManager.getInstance().searchByRequestId(requestID);
        if (request.getRequestStat().equals("Pending")) {

            
            FriendManager senderManager = FriendManagerFactory.getFriendManager(sender.getUserID());
            FriendManager recipientManager = FriendManagerFactory.getFriendManager(recipient.getUserID());

            // Add each other as friends
            senderManager.getFriends().add(recipient);
            recipientManager.getFriends().add(sender);


            // Save the updated data to the friends file
            senderManager.saveUserData();
            recipientManager.saveUserData();
            requestStat = "Accepted";

            //Save to File
            RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
            FriendManagerFactory.getFriendManager(recipient.getUserID()).saveUserData();

            System.out.println("Friend request from " + sender.getUsername() + " accepted.");
        } else {
            System.out.println("No pending friend request from " + sender.getUsername());
        }
    }

    public void processDeclineFriendRequest() {
        Request request = RequestsFileManager.getInstance().searchByRequestId(requestID);
        if (request.getRequestStat().equals("Pending")) {

            requestStat = "Declined";

            //Save to File
            RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());

            System.out.println("Friend request from " + sender.getUsername() + " declined.");
        } else {
            System.out.println("No friend request from " + sender.getUsername());
        }
    }

}
