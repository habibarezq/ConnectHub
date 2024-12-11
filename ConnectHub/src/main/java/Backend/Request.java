package Backend;

import java.util.UUID;

public class Request {

    private User sender;
    private User recipient;
    private String requestStat;
    private String requestID;

    public Request(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.requestStat="Pending";
        this.requestID=UUID.randomUUID().toString();
        
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
        if (recipient.searchRequest(sender)!=null && !recipient.getBlocked().contains(sender)) {
            recipient.getFriendRequests().add(this);
            requestStat="Pending";
            System.out.println("Friend Request sent From " + sender.getUsername() + " --> " + recipient.getUsername());
        } else if (recipient.searchRequest(sender)!=null) {
            System.out.println("Friend request already sent.");
        } else if (recipient.getBlocked().contains(sender)) {

        }

    }

    public void processAcceptFriendRequest() {
        if (recipient.searchRequest(sender)!=null && recipient.searchRequest(sender).getRequestStat().equals("Pending")) {
            recipient.getFriendRequests().remove(this);
             sender.getFriendRequests().remove(this);
            recipient.getFriends().add(sender);
            sender.getFriends().add(recipient);
            System.out.println("Friend request from " + sender.getUsername() + " accepted.");
        } else {
            System.out.println("No pending friend request from " + sender.getUsername());
        }
    }

    public void processDeclineFriendRequest() {
        if (recipient.searchRequest(sender)!=null && recipient.searchRequest(sender).getRequestStat().equals("Pending")) {
           recipient.getFriendRequests().remove(this);
           sender.getFriendRequests().remove(this);
            System.out.println("Friend request from " + sender.getUsername() + " declined.");
        } else {
            System.out.println("No friend request from " + sender.getUsername());
        }
    }
    
   
    
}
