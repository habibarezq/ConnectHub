
package Backend.GroupManagement;

import Backend.UserManagement.User;
import java.util.ArrayList;

public class NormalAdmin extends Admin {

    public NormalAdmin(String groupUserId) {
        super(groupUserId);
    }
    
    
    //accept/decline memebership requests
    //remove members  OVERRIDEN
    //manage posts    OVERRIDEN
}
