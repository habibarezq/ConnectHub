
package Backend.GroupManagement;

import Backend.FileManagers.UserFileManager;
import Backend.UserManagement.User;

public class GroupUser {
    
    private String groupUserId;
    private User user;
    
    public GroupUser(String groupUserId) {
        this.groupUserId = groupUserId;
        this.user = UserFileManager.getInstance().findUserByID(groupUserId);
    }

    public String getGroupUserId() {
        return groupUserId;
    }

    public User getUser() {
        return user;
    }
    
    public void createPost(String groupId){
        
        
    }
}
