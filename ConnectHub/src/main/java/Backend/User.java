package Backend;


import Backend.FileManagers.FriendsFileManager;
import Backend.FileManagers.RequestsFileManager;
import Backend.FileManagers.UserFileManager;
import java.time.*;
import java.util.*;
import Interfaces.*;

public class User implements FriendshipManager, FriendRequestService {

    private String userID;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    protected boolean status;
    //Each Friends,posts,stories will have its own database service

    private ContentManager contentManager;
    private FriendsFileManager friendsManager;
    private ArrayList<User> friends;
    private ArrayList<User> blocked;


    public User(String userID, String email, String username, LocalDate dateOfBirth, String password) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        status = false;

        this.friends=new ArrayList<>();
        this.blocked=new ArrayList<>();
        this.contentManager=ContentManager.getInstance(userID);
        
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<User> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<User> blocked) {
        this.blocked = blocked;
    }

    public boolean isStatus() {
        return status;
    }

    public ContentManager getContentManager() {
        return contentManager;
    }
    
    public FriendsFileManager getFriendsManager()
    {
        return friendsManager;
    }

    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String buserID) {
        this.userID = buserID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void removeFriend(User friend) {
        if (this.getFriends().contains(friend)) {
            this.getFriends().remove(friend);
            friend.getFriends().remove(this);
            System.out.println(friend.getUsername() + " has been removed from your friend list.");
        } else {
            System.out.println(friend.getUsername() + " is not in your friend list");
    }
    }

    @Override
    public void blockFriend(User friend) {
        if (this.getFriends().contains(friend)) {
            this.getFriends().remove(friend);
            friend.getFriends().remove(this);
            this.getBlocked().add(friend);
            System.out.println(friend.getUsername() + " has been blocked from.");
           
        } else {
            System.out.println(friend.getUsername() + " is not in your friend list");
        }
    }

    @Override
    public void sendRequest(User recipient) {
        Request friendRequest = new Request(this, recipient);
        friendRequest.processFriendRequest();
    }

    @Override
    public void acceptRequest(User sender) {
       // Request friendRequest = searchRequest(sender);
      //  friendRequest.processAcceptFriendRequest();
    }
  
    @Override
    public void declineRequest(User sender) {
      //  Request friendRequest = searchRequest(sender);
       // friendRequest.processDeclineFriendRequest();
    }

    @Override
    public ArrayList<User> suggestFriends(ArrayList<User> allUsers) {
        ArrayList<User> suggestions=new ArrayList<>();
        
        for(User user: allUsers)
        {
            if(user !=this && !this.getFriends().contains(user) && /*RequestManager.getInstance(userID).search() */ !this.getBlocked().contains(user))
                suggestions.add(user);
        }
        return suggestions;
    }
    
  
    public void logout() {
        if (this != null) {
            this.setStatus(false);
            UserFileManager.getInstance().saveToFile(UserFileManager.getInstance().getUsers());
        }
    }
    @Override
    public void displayStatuses() {
    }
}
