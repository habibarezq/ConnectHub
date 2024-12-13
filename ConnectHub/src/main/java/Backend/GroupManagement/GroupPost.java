
package Backend.GroupManagement;

import Backend.Post;
import java.time.LocalDateTime;


public class GroupPost extends Post{
    private String groupId;

    public GroupPost(String groupId, String authorId, String contentTxt, String contentPath, LocalDateTime uploadingTime) {
        super(authorId, contentTxt, contentPath, uploadingTime);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    
    
}
