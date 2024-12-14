
package Frontend;

import Backend.GroupManagement.Group;
import Frontend.ConnectHubMain;

public class RoleManager extends javax.swing.JFrame {

    public RoleManager() {
    }

 public  void checkRole(Group group,String userId,NewsfeedPage newsfeed )
 {
       if(group.isCreator(userId))
            {
                new CreatorGroupPage(newsfeed, group.getGroupId()).setVisible(true);
                this.dispose();
            }
            else if(group.isAdmin(userId))
            {
                new AdminGroupPage(newsfeed, group.getGroupId()).setVisible(true);
                this.dispose();
            }
            else if(group.isMember(userId))
            {
                new UserGroupPage(newsfeed,group.getGroupId()).setVisible(true);
                this.dispose();
            }
 }
}
