package Backend.FriendManagment;

import Backend.FileManagers.*;
import Backend.UserManagement.User;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FriendManager {

    private final String userId;
    private final ArrayList<User> friends;
    private final ArrayList<User> blocked;

    // Constructor is private to enforce the factory usage
    FriendManager(String userId) {
        this.userId = userId;
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        loadUserData();
    }

    private void loadUserData() {
        JSONObject userData = FriendsFileManager.getInstance().getUserData(userId);
        if (userData == null) {
            return; // No data found for the user
        }

        JSONArray friendsArray = userData.optJSONArray("friends");
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                String friendId = friendsArray.getString(i);
                User friend = UserFileManager.getInstance().findUserByID(friendId);
                if (friend != null) {
                    friends.add(friend);
                }
            }
        }

        JSONArray blockedArray = userData.optJSONArray("blocked");
        if (blockedArray != null) {
            for (int i = 0; i < blockedArray.length(); i++) {
                String blockedId = blockedArray.getString(i);
                User blockedUser = UserFileManager.getInstance().findUserByID(blockedId);
                if (blockedUser != null) {
                    blocked.add(blockedUser);
                }
            }
        }
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getBlocked() {
        return blocked;
    }

    public void saveUserData() {
        JSONObject userData = new JSONObject();
        userData.put("userId", userId);

        JSONArray friendsArray = new JSONArray();
        for (User friend : friends) {
            friendsArray.put(friend.getUserID());
        }
        userData.put("friends", friendsArray);

        JSONArray blockedArray = new JSONArray();
        for (User blockedUser : blocked) {
            blockedArray.put(blockedUser.getUserID());
        }
        userData.put("blocked", blockedArray);

        FriendsFileManager.getInstance().updateUserData(userId, userData);
        FriendsFileManager.getInstance().saveToFile();
    } 
    //NOTE THAT The saveUserData() saves the user's friends and blocked lists back to the friends file. 
    //It updates the corresponding JSON object for the user and saves it using FriendsFileManager.
}
