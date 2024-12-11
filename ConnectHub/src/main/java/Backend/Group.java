package Backend;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private String groupId;
    private User creator;
    private ArrayList<User> admins;
    private ArrayList<User> members;
    private String description;
    private String imagePath;

    public Group(String groupName, String groupId, User creator, ArrayList<User> admins, ArrayList<User> members, String description, String imagePath) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.creator = creator;
        this.admins = admins;
        this.members = members;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<User> admins) {
        this.admins = admins;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
}
