
package Interfaces;

import Backend.User;
import java.util.ArrayList;


public interface FriendshipManager {
    //removeFriend: Ends the friendship but allows users to reappear in friend suggestions 
    public void removeFriend(String userToRemoveUsername);
    //blockFriend:  Prevent interaction, including viewing posts, stories, and statuses 
    public void blockFriend(String userToBlockUsername);
 
    public ArrayList<User> suggestFriends();
    
}
