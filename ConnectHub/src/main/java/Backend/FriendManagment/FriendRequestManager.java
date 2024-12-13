package Backend.FriendManagment;

import Backend.FileManagers.*;
import Backend.Request;
import Backend.UserManagement.User;
import Interfaces.*;
import java.util.ArrayList;

public class FriendRequestManager implements FriendRequestService {

    private final String userId;
    private static FriendRequestManager instance;
    private final User user;

    private FriendRequestManager(String userId) {
        this.userId = userId;
        this.user = UserFileManager.getInstance().findUserByID(userId);

    }

    public static synchronized FriendRequestManager getInstance(String userId) {
        if (instance != null && !instance.userId.equals(userId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new FriendRequestManager(userId);
        }
        return instance;
    }

    @Override
    public void sendRequest(User recipient) {
        Request friendRequest = new Request(user, recipient);
        friendRequest.processFriendRequest();
    }

    @Override
    public void acceptRequest(User sender) {
        Request friendRequest = RequestsFileManager.getInstance().searchForRequestByIds(sender.getUserID(), userId);
        friendRequest.processAcceptFriendRequest();
    }

    @Override
    public void declineRequest(User sender) {
        Request friendRequest = RequestsFileManager.getInstance().searchForRequestByIds(sender.getUserID(), userId);
        friendRequest.processDeclineFriendRequest();
    }

}
