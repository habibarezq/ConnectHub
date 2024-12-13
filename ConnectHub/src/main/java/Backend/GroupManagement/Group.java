package Backend.GroupManagement;

import Backend.Post;
import java.util.ArrayList;

public class Group {

    private String name;
    private String description;
    private String photoPath;
    private String groupId;
    private String creatorId;       // id of the creator "Primary admin" of the group

    private ArrayList<Post> posts;

    public Group(String name, String description, String photoPath, String groupId, String creatorId) {
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.groupId = groupId;
        this.creatorId = creatorId;

        this.posts = new ArrayList<>();
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

}
