
package Backend.GroupManagement;

import Backend.ContentManager;
import Backend.FileManagers.GroupMembershipFileManager;
import java.util.ArrayList;
//GroupManager.getInstance(user,group or groupId).remove/accept/decline and so on

public class GroupManager { //IDEA ON HOW TO USE INSTEAD OF WRITING FUNCTIONS IN SUBCLASSES 
    private GroupUser user;
    private Group group;
    private static GroupManager instance;

    private GroupManager(Admin user, Group group) {
        this.user = user;
        this.group = group;
    }
     public static synchronized GroupManager getInstance(Admin user, Group group) {
        if (instance != null && !instance.user.equals(user)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new GroupManager(user,group);
        }
        return instance;
    }

    public GroupUser getUser() {
        return user;
    }

    public Group getGroup() {
        return group;
    }
    
    public void acceptMember(String memberToAddId){
        ArrayList<GroupUser> members = MembershipManager.getInstance(group.getGroupId()).getGroupUsers();
        if(group.isAdmin(user.getGroupUserId()))
        {
         GroupUser memberToAdd = new GroupUser(memberToAddId);
            System.out.println("Added ");
         members.add(memberToAdd);
        }
        
        //remove Request
        //save to GroupRequests File
        MembershipManager.getInstance(group.getGroupId()).saveUserData();
        GroupMembershipFileManager.getInstance().saveToFile();
    }
    
    public void declineMember(String memberToDeclineId){
        
        
        //remove request
        //SAVE TO FILE GroupRequests
    }
    
    public void removeMember(String toBeRemoveId)
    {
    ArrayList<GroupUser> members = MembershipManager.getInstance(group.getGroupId()).getGroupUsers();
        if(group.isAdmin(user.getGroupUserId()))
        {
         GroupUser memberToRemove = MembershipManager.getInstance(group.getGroupId()).getGroupUserById(toBeRemoveId);
         members.remove(memberToRemove);
        }
        
        MembershipManager.getInstance(group.getGroupId()).saveUserData();
        GroupMembershipFileManager.getInstance().saveToFile();    
    }
}

