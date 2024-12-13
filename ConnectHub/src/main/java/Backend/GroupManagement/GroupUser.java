package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.UserFileManager;
import Backend.UserManagement.User;
import java.util.ArrayList;

public class GroupUser {

    private String groupUserId;

    public GroupUser(String groupUserId) {
        this.groupUserId = groupUserId;
    }

    public String getGroupUserId() {
        return groupUserId;
    }

    public User getUser(String Id) {
        User user = UserFileManager.getInstance().findUserByID(Id);
        return user;
    }

    public void deleteGroup(String groupId) {

        ArrayList<GroupUser> members = MembershipManager.getInstance(groupId).getGroupUsers();

        GroupUser memberToRemove = MembershipManager.getInstance(groupId).getGroupUserById(groupUserId);

        members.remove(memberToRemove);
        GroupMembershipFileManager.getInstance().saveToFile();
        MembershipManager.getInstance(groupId).saveUserData();
    }

}
