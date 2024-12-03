package Backend;

import java.time.*;
import java.util.ArrayList;

public class User implements UserInterface {

    private String userID;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    protected String status;
    private ArrayList friends;
    private ArrayList posts;
    private ArrayList stories;

    public User(String userID, String email, String username, String password, LocalDate dateOfBirth) {
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
    public void block(User other) {

    }

    @Override
    public void removeFriend(User other) {
        getFriends().remove(other);
    }

    @Override
    public void sendRequest(User other) {
    }

    @Override
    public void logout() {
        setStatus("offline");
    }

}
