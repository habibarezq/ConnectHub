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
    
    public void processFriendRequest()
    {
        System.out.println("Processing Friend Request ...");
        System.out.println("Friend Request sent From "+sender.getUsername()+" --> "+recipient.getUsername());
    }

    public void processAcceptFriendRequest()
    {
        System.out.println("Accepted Friend Request ...");
        System.out.println(sender.getUsername()+" Accepted "+ recipient.getUsername());
    }
    
    public void processDeclineFriendRequest()
    {
        //System.out.println("Declined Friend Request ...");
        System.out.println(sender.getUsername()+" Declined "+ recipient.getUsername());
    }
}
