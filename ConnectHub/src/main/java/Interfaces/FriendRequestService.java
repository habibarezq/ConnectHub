
package Interfaces;

//import Backend.Request;
import Backend.UserManagement.User;


public interface FriendRequestService {
    public void sendRequest(User recipient);
    public void acceptRequest(User sender);
    public void declineRequest(User sender);
    
  
}
