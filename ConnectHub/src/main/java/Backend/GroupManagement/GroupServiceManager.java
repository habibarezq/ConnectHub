package Backend.GroupManagement;

import Backend.ContentManager;
import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.GroupsFileManager;
import Backend.FileManagers.UserFileManager;
import Backend.FriendManagment.FriendManager;
import Backend.FriendManagment.FriendManagerFactory;
import Backend.RequestManager;
import Backend.UserManagement.User;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroupServiceManager { //Greates the group only 

    private GroupsFileManager groupsFileManager = GroupsFileManager.getInstance();
    private GroupMembershipFileManager membershipFileManager = GroupMembershipFileManager.getInstance();

    public Group createGroup(String name, String description, String photoPath, String creatorId) {
        Group group = new Group(name, description, photoPath, creatorId);
        
        groupsFileManager.getGroups().add(group);
        groupsFileManager.saveToFile(groupsFileManager.getGroups());

        MembershipManager.getInstance(group.getGroupId()).saveUserData();
        return group;
    }

    
}


