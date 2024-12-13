package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import Backend.FileManagers.GroupsFileManager;
import java.util.ArrayList;

public class PrimaryAdmin extends Admin {

    public PrimaryAdmin(String groupUserId) {
        super(groupUserId);
    }

    // public abstract void removeMember(String Id); OVERRIDEN
    //promote/demote admins
    public void promote(String Id) {

        ArrayList<GroupUser> members = MembershipManager.getInstance(Id).getGroupUsers();
        ArrayList<NormalAdmin> admins = MembershipManager.getInstance(Id).getAdmins();
        GroupUser memberToPromote = null;

        for (GroupUser m : members) {
            String currentId = m.getGroupUserId();
            if (currentId.equals(Id)) {

                memberToPromote = m;
                NormalAdmin promotedMember = new NormalAdmin(memberToPromote.getGroupUserId());
                admins.add(promotedMember); // adding the member after promotimg to be an admin to the admin ArrayList
                members.remove(memberToPromote); // removing the member after promotimg to be an admin fom the users ArrayList

                GroupMembershipFileManager.getInstance().saveToFile();

                return;
            }
        }

        if (memberToPromote == null) { // of not found
            return;
        }
    }

    public void demote(String Id) {
        ArrayList<GroupUser> members = MembershipManager.getInstance(Id).getGroupUsers();
        ArrayList<NormalAdmin> admins = MembershipManager.getInstance(Id).getAdmins();

        NormalAdmin adminToDemote = null;
        for (NormalAdmin a : admins) {
            String currentId = a.getGroupUserId();
            if (currentId.equals(Id)) {

                adminToDemote = a;
                GroupUser demotedAdmin = new GroupUser(adminToDemote.getGroupUserId());
                members.add(demotedAdmin); // adding the member after promotimg to be an admin to the admin ArrayList
                admins.remove(adminToDemote); // removing the member after promotimg to be an admin fom the users ArrayList

                GroupMembershipFileManager.getInstance().saveToFile();
                return;
            }
        }

        if (adminToDemote == null) { // of not found
            return;
        }
    }

    //delete group
    @Override
    public void deleteGroup(String groupId) {
        ArrayList<Group> groups = GroupsFileManager.getInstance().getGroups();

        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) {
                groups.remove(group);
            }
        }
        GroupsFileManager.getInstance().saveToFile(groups);
    }

    //manage posts    OVERRIDEN
}
