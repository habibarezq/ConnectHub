package Backend.GroupManagement;

import Backend.FileManagers.FriendsFileManager;
import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.GroupsFileManager;
import Backend.FileManagers.UserFileManager;
import Backend.ProfileManager;
import Backend.UserManagement.User;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class MembershipManager {

    private final String groupId;
    private static MembershipManager instance;
    private ArrayList<NormalAdmin> admins;
    private ArrayList<GroupUser> groupUsers;

    private MembershipManager(String groupId) {
        this.groupId = groupId;
        this.admins = new ArrayList<>();
        this.groupUsers = new ArrayList<>();
        loadMembershipData();
    }

    public static synchronized MembershipManager getInstance(String groupId) {
        if (instance != null && !instance.groupId.equals(groupId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new MembershipManager(groupId);
        }
        return instance;
    }

    private void loadMembershipData() {
        JSONObject groupData = GroupMembershipFileManager.getInstance().getUserData(groupId);
        if (groupData == null) {
            return; // No data found for the user
        }

        JSONArray AdminsArray = groupData.optJSONArray("admins");
        if (AdminsArray != null) {
            for (int i = 0; i < AdminsArray.length(); i++) {
                String adminId = AdminsArray.getString(i);
                NormalAdmin admin = new NormalAdmin(adminId);

                if (admin != null) {
                    admins.add(admin);
                }
            }
        }

        JSONArray usersArray = groupData.optJSONArray("users");
        if (usersArray != null) {
            for (int i = 0; i < usersArray.length(); i++) {
                String userId = usersArray.getString(i);
                //User user = UserFileManager.getInstance().findUserByID(userId);
                GroupUser user = new GroupUser(userId);
                if (user != null) {
                    groupUsers.add(user);
                }
            }
        }
    }

    public ArrayList<NormalAdmin> getAdmins() {
        return admins;
    }

    public ArrayList<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void saveUserData() {
        JSONObject userData = new JSONObject();
        userData.put("groupId", groupId);

        JSONArray adminsArray = new JSONArray();
        for (NormalAdmin admin : admins) {
            adminsArray.put(admin.getGroupUserId());
        }
        userData.put("admins", adminsArray);

        JSONArray usersArray = new JSONArray();
        for (GroupUser user : groupUsers) {
            usersArray.put(user.getGroupUserId());
        }
        userData.put("users", usersArray);

        GroupMembershipFileManager.getInstance().updateUserData(groupId, userData);
        GroupMembershipFileManager.getInstance().saveToFile();
    }

    public GroupUser getGroupUserById(String Id) {
        for (GroupUser u : groupUsers) {
            if (Id.equals(u.getGroupUserId())) {
                return u;
            }
        }
        return null;
    }

    public GroupUser getGroupUserByUsername(String username) {
        for(GroupUser user:groupUsers)
        {
            User grpUser=user.getUser(user.getGroupUserId());
            if(grpUser.getUsername().equals(username))
                return user;
        }
        return null;
    }
    
    
    //NOTE THAT The saveUserData() saves the user's friends and blocked lists back to the friends file. 
    //It updates the corresponding JSON object for the user and saves it using FriendsFileManager.
}
