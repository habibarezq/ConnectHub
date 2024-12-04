
package Interfaces;

import Backend.User;


public interface FriendshipManager {
    //removeFriend: Ends the friendship but allows users to reappear in friend suggestions 
    public void removeFriend(User recipient);
    //blockFriend:  Prevent interaction, including viewing posts, stories, and statuses 
    public void blockFriend(User recipient);
    //Not sure ha7ot dy wala la2 hena
    public void displayStatuses ();
    public void suggestFriends(User recipient);
}
