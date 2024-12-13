
package Backend.GroupManagement;

import Backend.FileManagers.GroupRequestsFileManager;
import java.util.ArrayList;

public class GroupRequestManager {

    private final String groupId;
    private static GroupRequestManager instance;
    private ArrayList<GroupRequest> groupRequest;

    private boolean requestsLoaded = false;

    private GroupRequestManager(String groupId) {
        this.groupId = groupId;
        this.groupRequest = new ArrayList<>();
    }

    public static synchronized GroupRequestManager getInstance(String groupId) {
        if (instance != null && !instance.groupId.equals(groupId)) {
            // Clear the existing instance when the groupId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new GroupRequestManager(groupId);
        }
        return instance;
    }

    public ArrayList<GroupRequest> getGroupRequests() {
        loadRequests();
        return groupRequest;
    }

    public void loadRequests() {
        if (!requestsLoaded) {
            for (GroupRequest request : GroupRequestsFileManager.getInstance().getRequests()) {
                if (request.getGroup().getGroupId().equals(groupId) && request.getRequestStat().equals("Pending")) {
                    this.groupRequest.add(request);
                }
            }
        }
        requestsLoaded = true;
    }

    public void refreshRequests() {

        this.groupRequest = null;
    }

}
