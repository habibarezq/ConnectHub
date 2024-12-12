
package Backend;

import java.util.*;

public class Group {
    
    private String name;
    private String description;
    private String photoPath;
    private String groupId;
    
    private String creatorId;       // id of the creator "Primary admin" of the group
    private ArrayList<User> admins; // List of normal admins
    private ArrayList<User> users ; // List of normal users

    public Group(String name, String description, String photoPath, String groupId, String creatorId, ArrayList<User> admins, ArrayList<User> users) {
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.groupId = groupId;
        this.creatorId = creatorId;
        this.admins = admins;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
}
