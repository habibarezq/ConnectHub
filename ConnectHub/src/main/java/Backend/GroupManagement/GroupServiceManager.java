package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.GroupsFileManager;
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


