
package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import java.util.ArrayList;

public class NormalAdmin extends Admin {

    public NormalAdmin(String groupUserId) {
        super(groupUserId);
    }

    //accept/decline memebership requests
    public void acceptMember(String Id){
        ArrayList<GroupUser> members = MembershipManager.getInstance(Id).getGroupUsers();
        GroupUser memberToAdd = new GroupUser(Id);
        members.add(memberToAdd);
        //remove Request
        //save to GroupRequests File
        
        GroupMembershipFileManager.getInstance().saveToFile();
    }
    
    public void decline(String Id){
    
        //remove request
        //SAVE TO FILE GroupRequests
    }
    
    //remove members  OVERRIDEN
    //manage posts    OVERRIDEN
}
