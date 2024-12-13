package Backend.GroupManagement;

import Backend.FileManagers.*;
import Backend.RequestManager;
import Interfaces.*;
import java.util.UUID;

public class GroupRequest implements GroupRequestService {

    private GroupUser user;
    private Group group;
    private String requestStat;
    private String requestId;

    public GroupRequest(GroupUser user, Group group) {
        this.user= user;
        this.group= group;
        this.requestStat = "Pending";
        this.requestId = UUID.randomUUID().toString();
    }

    public GroupUser getUser() {
        return user;
    }

    public void setUser(GroupUser user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getRequestStat() {
        return requestStat;
    }

    public void setRequestStat(String requestStat) {
        this.requestStat = requestStat;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public void sendRequest(String groupId) {
        System.out.println("Processing Group Request To Join...");

        requestStat = "Pending";

        GroupRequestsFileManager.getInstance().getRequests().add(this);
        GroupRequestManager.getInstance(groupId).getGroupRequests().add(this);
        //MUST ADD GROUP TO ARRAYLIST OF GROUPS FOR THIS USER
        System.out.println("Group Request sent From " + user.getGroupUserId() + " --> " + group.getName());
        } 

}
