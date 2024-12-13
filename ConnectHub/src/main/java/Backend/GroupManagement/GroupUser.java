
package Backend.GroupManagement;

import Backend.FileManagers.UserFileManager;
import Backend.UserManagement.User;

public class GroupUser  {
    
    private String groupUserId;
    

    public GroupUser(String groupUserId) {
        this.groupUserId = groupUserId;
    }

    public String getGroupUserId() {
        return groupUserId;
    }

    public User getUser(String Id)
    {
        User user=UserFileManager.getInstance().findUserByID(Id);
        return user;
    }
    public void createPost(String groupId){
        
        
    }
}
