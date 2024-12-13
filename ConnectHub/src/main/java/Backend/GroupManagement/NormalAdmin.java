
package Backend.GroupManagement;

import Backend.FileManagers.GroupMembershipFileManager;
import java.util.ArrayList;

public class NormalAdmin extends Admin {

    public NormalAdmin(String groupUserId) {
        super(groupUserId);
    }

    
    
    public void decline(String Id){
    
        //remove request
        //SAVE TO FILE GroupRequests
    }
    
    //remove members  OVERRIDEN
    //manage posts    OVERRIDEN
}
