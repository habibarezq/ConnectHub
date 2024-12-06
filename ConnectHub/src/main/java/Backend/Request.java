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
        if (!recipient.getFriendRequests().containsKey(sender) && !recipient.getFriendsManager().getBlocked().contains(sender)) {
            recipient.getFriendRequests().put(sender, "Pending");
            System.out.println("Friend Request sent From " + sender.getUsername() + " --> " + recipient.getUsername());
        } else if (recipient.getFriendRequests().containsKey(sender)) {
            System.out.println("Friend request already sent.");
        } else if (recipient.getFriendsManager().getBlocked().contains(sender)) {

        }

    }

    public void processAcceptFriendRequest() {
        if (recipient.getFriendRequests().containsKey(getSender()) && recipient.getFriendRequests().get(sender).equals("Pending")) {
            recipient.getFriendRequests().put(sender, "Accepted");
            recipient.getFriendsManager().getFriends().add(sender);
            sender.getFriendsManager().getFriends().add(recipient);
            System.out.println("Friend request from " + sender.getUsername() + " accepted.");
        } else {
            System.out.println("No pending friend request from " + sender.getUsername());
        }
    }

    public void processDeclineFriendRequest() {
        if (recipient.getFriendRequests().containsKey(getSender()) && recipient.getFriendRequests().get(sender).equals("Pending")) {
            recipient.getFriendRequests().put(sender, "Declined");

            System.out.println("Friend request from " + sender.getUsername() + " declined.");
        } else {
            System.out.println("No friend request from " + sender.getUsername());
        }
    }
}
