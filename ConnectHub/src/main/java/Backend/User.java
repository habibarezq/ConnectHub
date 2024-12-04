package Backend;

import Interfaces.FriendRequestService;
import java.time.*;
import java.util.ArrayList;
import Interfaces.FriendshipManager;

public class User implements UserInterface, FriendshipManager, FriendRequestService {

    private String userID;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    protected String status;
    //Each Friends,posts,stories will have its own database service
    private ArrayList friends;
    private ArrayList posts;
    private ArrayList stories;

    public User(String userID, String email, String username, LocalDate dateOfBirth ,String password) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        status = "online";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList getFriends() {
        return friends;
    }

    public void setFriends(ArrayList friends) {
        this.friends = friends;
    }

    public ArrayList getPosts() {
        return posts;
    }

    public void setPosts(ArrayList posts) {
        this.posts = posts;
    }

    public ArrayList getStories() {
        return stories;
    }

    public void setStories(ArrayList stories) {
        this.stories = stories;
    }


    @Override
    public void removeFriend(User other) {
       /*  try {
            User user = UserManager.findUser(other.getUserID());

            if (user != null) {
                userRepository.deleteById(id);
                return "Friend Removed successfully!";
            } else {
                return "Friend not found";
            }
        } catch (Exception e) {
            return "An error occurred while removing User";
        }*/
        getFriends().remove(other);
    }

    

    @Override
    public void blockFriend(User other) {
        getFriends().remove(other);
        //Should block interactions and feed of blocked user and not show in suggestions
    }
    @Override
    public void sendRequest(User other) {
        Request friendRequest=new Request(this,other);
        friendRequest.processFriendRequest();

    }


    @Override
    public void suggestFriends(User other) {
        System.out.println("Friends Suggestions ... ");
        //Is it more logical to move this function elsewhere and send user as argument
        //and compare users Friends' List and Users' List to print suggestions ?
        
    }

    @Override
    public void displayStatuses () {
        System.out.println("All Friends + Their Statuses ... ");
    }
    
    @Override
    public void acceptRequest(Request friendRequest) {
        friendRequest.processAcceptFriendRequest();
        this.friends.add(friendRequest.getRecipient());
        
    }

    @Override
    public void declineRequest(Request friendRequest) {
        friendRequest.processDeclineFriendRequest();
    }

    @Override
    public void logout() {
        setStatus("Offline");
    }

}
