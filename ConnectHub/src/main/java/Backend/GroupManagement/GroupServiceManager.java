package Backend.GroupManagement;

import Backend.FileManagers.GroupsFileManager;
import java.util.ArrayList;

public class GroupServiceManager { //Greates the group only 

    private static ArrayList<Group> allGroups;

    public static Group createGroup(String name, String description, String photoPath, String creatorId) {
        allGroups=GroupsFileManager.getInstance().getGroups();
        Group group = new Group(name,description,photoPath,creatorId);
        group.setGroupId(java.util.UUID.randomUUID().toString());
        allGroups.add(group);
        
        //Save to File
        GroupsFileManager.getInstance().saveToFile(allGroups);
        return group;
    }

    public static ArrayList<Group> getAllGroups() {
        return allGroups;
    }
}

