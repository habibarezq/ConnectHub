package Backend;

import Interfaces.FilePaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Interfaces.*;

public class FriendsFileManager implements FileManager<User> {

    private static FriendsFileManager instance;
    private String userId;
    private ArrayList<User> friends;
    private ArrayList<User> blocked;
    private String FILE_PATH = FilePaths.FRIENDS_FILE_PATH;

    // Private constructor to avoid direct instantiation
    private FriendsFileManager(String userId) {
        this.userId = userId;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        readFromFile();
    }

    // Singleton instance to ensure only one instance for a user
    public static synchronized FriendsFileManager getInstance(String userId) {
        if (instance == null || !instance.userId.equals(userId)) {
            instance = new FriendsFileManager(userId);
        }
        return instance;
    }

    @Override
    public void readFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray allUsersFriendsArray = new JSONArray(json); // Parse the JSON array

            for (int i = 0; i < allUsersFriendsArray.length(); i++) {
                JSONObject userFriendBlockedJSON = allUsersFriendsArray.getJSONObject(i);
                String currentUserId = userFriendBlockedJSON.getString("userId");

                // Check if we are dealing with the current user's data
                if (currentUserId.equals(this.userId)) {
                    // Get friends and blocked lists
                    JSONArray friendsArray = userFriendBlockedJSON.getJSONArray("friends");
                    JSONArray blockedArray = userFriendBlockedJSON.getJSONArray("blocked");

                    // Convert friends and blocked users' JSON data to ArrayList<User>
                    for (int j = 0; j < friendsArray.length(); j++) {
                        String friendId = friendsArray.getString(j);
                        User friend = UserFileManager.getInstance().findUserByID(friendId);
                        if (friend != null) {
                            friends.add(friend);
                        }
                    }

                    for (int j = 0; j < blockedArray.length(); j++) {
                        String blockedId = blockedArray.getString(j);
                        User blockedUser = UserFileManager.getInstance().findUserByID(blockedId);
                        if (blockedUser != null) {
                            blocked.add(blockedUser);
                        }
                    }
                }
            }
        } catch (IOException | JSONException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<User> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray allUsersFriendsArray = new JSONArray();

        for (User user : data) {
            JSONObject userFriendBlockedJSON = new JSONObject();
            userFriendBlockedJSON.put("userId", user.getUserID());

            JSONArray friendsArray = new JSONArray();
            for (User friend : getFriends()) {
                friendsArray.put(friend.getUserID()); // Add friend IDs to the JSON array
            }
            userFriendBlockedJSON.put("friends", friendsArray);

            JSONArray blockedArray = new JSONArray();
            for (User blockedUser : getBlocked()) {
                blockedArray.put(blockedUser.getUserID()); // Add blocked user IDs to the JSON array
            }
            userFriendBlockedJSON.put("blocked", blockedArray);

            allUsersFriendsArray.put(userFriendBlockedJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(allUsersFriendsArray.toString(4));  // Pretty print with an indentation of 4 spaces
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Getter for friends
    public ArrayList<User> getFriends() {
        return friends;
    }

    // Setter for friends
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    // Getter for blocked users
    public ArrayList<User> getBlocked() {
        return blocked;
    }

    // Setter for blocked users
    public void setBlocked(ArrayList<User> blocked) {
        this.blocked = blocked;
    }

    // Add a friend to the user's friends list
    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            saveToFile(new ArrayList<>());  // Make sure to save after modifying
        }
    }

    // Add a user to the blocked list
    public void addBlocked(User blockedUser) {
        if (!blocked.contains(blockedUser)) {
            blocked.add(blockedUser);
            saveToFile(new ArrayList<>());  // Save after blocking someone
        }
    }
}
