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
    private String FILE_PATH = FilePaths.FRIENDS_FILE_PATH;
    private String userId;
    private ArrayList<User> friends; // List of the current user's friends
    private ArrayList<User> blocked; // List of the current user's blocked users

    // Private constructor to avoid instantiation (singleton)
    private FriendsFileManager(String userId) {
        this.userId = userId;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        readFromFile(); // Load the user's friends and blocked users on initialization
    }

    // Singleton pattern: getInstance method
    public static synchronized FriendsFileManager getInstance(String userId) {
        if (instance == null) {
            instance = new FriendsFileManager(userId);
        }
        return instance;
    }

    public ArrayList<User> getFriends() {
    if (friends == null) {
        friends = new ArrayList<>();
    }
    return friends;
}

public ArrayList<User> getBlocked() {
    if (blocked == null) {
        blocked = new ArrayList<>();
    }
    return blocked;
}


    @Override
    public void readFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray allUsersFriendsArray = new JSONArray(json); // Parse the JSON array

            // Iterate through each user in the friends file
            for (int i = 0; i < allUsersFriendsArray.length(); i++) {
                JSONObject userFriendBlockedJSON = allUsersFriendsArray.getJSONObject(i);
                String userId = userFriendBlockedJSON.getString("userId");

                // Find the user using UserFileManager
                User user = UserFileManager.getInstance().findUserByID(userId);
                if (user != null) {
                    // Get the friends and blocked arrays
                    JSONArray friendsArray = userFriendBlockedJSON.getJSONArray("friends");
                    JSONArray blockedArray = userFriendBlockedJSON.getJSONArray("blocked");

                    ArrayList<User> friends = new ArrayList<>();
                    ArrayList<User> blocked = new ArrayList<>();

                    // Populate the friends list
                    for (int j = 0; j < friendsArray.length(); j++) {
                        String friendId = friendsArray.getString(j);
                        User friend = UserFileManager.getInstance().findUserByID(friendId);
                        if (friend != null) {
                            friends.add(friend);
                        }
                    }

                    // Update the user's friends list
                    user.setFriends(friends);

                    // Populate the blocked list
                    for (int j = 0; j < blockedArray.length(); j++) {
                        String blockedId = blockedArray.getString(j);
                        User blockedUser = UserFileManager.getInstance().findUserByID(blockedId);
                        if (blockedUser != null) {
                            blocked.add(blockedUser);
                        }
                    }

                    // Update the user's blocked list
                    user.setBlocked(blocked);
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
            for (User friend : user.getFriends()) {
                friendsArray.put(friend.getUserID());
            }
            userFriendBlockedJSON.put("friends", friendsArray);

            JSONArray blockedArray = new JSONArray();
            for (User blockedUser : user.getBlocked()) {
                blockedArray.put(blockedUser.getUserID());
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
}
