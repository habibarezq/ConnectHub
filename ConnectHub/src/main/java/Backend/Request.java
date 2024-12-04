package Backend;

public class Request {

    private User sender;
    private User recipient;

    public Request(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void processFriendRequest() {
        System.out.println("Processing Friend Request ...");
        if (!recipient.getFriendRequests().containsKey(sender) && !recipient.getBlocked().contains(sender)) {
            recipient.getFriendRequests().put(sender, "Pending");
            System.out.println("Friend Request sent From " + sender.getUsername() + " --> " + recipient.getUsername());
        } else if (recipient.getFriendRequests().containsKey(sender)) {
            System.out.println("Friend request already sent.");
        } else if (recipient.getBlocked().contains(sender)) {

        }

    }

    public void processAcceptFriendRequest() {
//        System.out.println("Accepted Friend Request ...");
//        System.out.println(sender.getUsername() + " Accepted " + recipient.getUsername());
        if (recipient.getFriendRequests().containsKey(getSender()) && recipient.getFriendRequests().get(sender).equals("Pending")) {
            recipient.getFriendRequests().put(sender, "Accepted");
            recipient.getFriends().add(sender);
            sender.getFriends().add(recipient);
            System.out.println("Friend request from " + sender.getUsername() + " accepted.");
        } else {
            System.out.println("No pending friend request from " + sender.getUsername());
        }
    }

    public void processDeclineFriendRequest() {
//        System.out.println("Declined Friend Request ...");
//        System.out.println(sender.getUsername() + " Declined " + recipient.getUsername());
        if (recipient.getFriendRequests().containsKey(getSender()) && recipient.getFriendRequests().get(sender).equals("Pending")) {
            recipient.getFriendRequests().put(sender, "Declined");

            System.out.println("Friend request from " + sender.getUsername() + " declined.");
        } else {
            System.out.println("No friend request from " + sender.getUsername());
        }
    }
}
