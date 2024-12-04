
package Interfaces;

import Backend.Request;
import Backend.User;


public interface FriendRequestService {
    public void sendRequest(User reciever);
    public void acceptRequest(Request friendRequest);
    public void declineRequest(Request friendRequest);
    
  
}
