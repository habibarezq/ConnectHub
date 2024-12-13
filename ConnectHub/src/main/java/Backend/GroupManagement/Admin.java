package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.GroupRequestsFileManager;
import Backend.FileManagers.UserFileManager;
import Backend.UserManagement.User;
import java.util.ArrayList;

public abstract class Admin extends GroupUser {

    public Admin(String groupUserId) {
        super(groupUserId);
    }

    public User getAdminUser(String Id) {
        User userAdmin = UserFileManager.getInstance().findUserByID(Id);
        return userAdmin;
    }

 public void acceptMember(String userId, String groupId) {
    ArrayList<GroupUser> members = MembershipManager.getInstance(groupId).getGroupUsers();
    GroupUser memberToAdd = new GroupUser(userId);
    members.add(memberToAdd);

    ArrayList<GroupRequest> allRequests = GroupRequestManager.getInstance(groupId).getGroupRequests();
    GroupRequest requestToRemove = null;
    for (GroupRequest request : allRequests) {
        if (request.getUser().getGroupUserId().equals(userId)) {
            requestToRemove = request;
            break;
        }
    }

    if (requestToRemove != null) {
        allRequests.remove(requestToRemove);
    } else {
        System.out.println("Request not found for user: " + userId);
    }

    GroupRequestsFileManager.getInstance().saveToFile(GroupRequestsFileManager.getInstance().getRequests());
    MembershipManager.getInstance(groupId).saveUserData();
    System.out.println("User " + userId + " has been accepted to group " + groupId);
}

}
