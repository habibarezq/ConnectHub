package Backend;

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
        this.requestStat="Pending";
        //this.requestID=UUID.randomUUID().toString();
        
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
            requestStat="Pending";
            
            //Save to File
            RequestsFileManager.getInstance().getRequests().add(this);
            for(Request r:RequestsFileManager.getInstance().getRequests())
            {
                System.out.println("Request "+r.getSender());
            }
            RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
            
            System.out.println("Friend Request sent From " + sender.getUsername() + " --> " + recipient.getUsername());
        } else  {
            System.out.println("Friend request already sent.");
        } 

    }

    public void processAcceptFriendRequest() {
        if ( searchRequest(this.getRequestID()).getRequestStat().equals("Pending")) {
            
            recipient.getFriends().add(sender);
            sender.getFriends().add(recipient);
            requestStat = "Accepted";
            
            //Save to File
            RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
            
            System.out.println("Friend request from " + sender.getUsername() + " accepted.");
        } else {
            System.out.println("No pending friend request from " + sender.getUsername());
        }
    }

    public void processDeclineFriendRequest() {
        if ( searchRequest(this.getRequestID()).getRequestStat().equals("Pending")) {
           requestStat = "Declined";
           
           //Save to File
           RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
           
            System.out.println("Friend request from " + sender.getUsername() + " declined.");
        } else {
            System.out.println("No friend request from " + sender.getUsername());
        }
    }
   
      public Request searchRequest(String requestId)
    {
        for(Request r:RequestsFileManager.getInstance().getRequests())
        {
            System.out.println(r);
            if(r.getRequestID().equals(requestId) )
                System.out.println("Found !");
                return r;
        }
        return null;
    }

}
