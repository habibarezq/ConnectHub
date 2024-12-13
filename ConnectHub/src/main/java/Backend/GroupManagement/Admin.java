package Backend.GroupManagement;

import Backend.FileManagers.UserFileManager;
import Backend.UserManagement.User;
import java.util.ArrayList;

public abstract class Admin extends GroupUser {

    public Admin(String groupUserId) {
        super(groupUserId);
    }
    
    public User getAdminUser(String Id)
    {
        User userAdmin=UserFileManager.getInstance().findUserByID(Id);
        return userAdmin;
    }

   
    //remove members 
    public void removeMember(String Id) { // momken n3ml keda fl front w neb3atlo el user ala tol

        ArrayList<GroupUser> members = MembershipManager.getInstance(Id).getGroupUsers();
        GroupUser memberToRemove = null;

        for (GroupUser m : members) {
            String currentId = m.getGroupUserId();
            if (currentId.equals(Id)) {

                members.remove(memberToRemove);
                
                //HOW TO SAVEEEE IN FILE HASHMAAAP
                return;
            }
        }

        if (memberToRemove == null) {
            return;
        }
    }

    //manage posts
}
