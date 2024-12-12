package Backend;

import Interfaces.*;
import java.util.ArrayList;
import Backend.FileManagers.*;

public class FriendServiceManager implements FriendshipManager {

    private User currentUser;
    private User otherUser;
    private static FriendServiceManager instance;

    private FriendServiceManager(User user) {
        this.currentUser = user;
        this.otherUser = null;
    }

    public static synchronized FriendServiceManager getInstance(User user) {
        if (instance != null && !instance.currentUser.equals(user)) {
            instance = null;  // Reinitialize the instance for new user
        }
        if (instance == null) {
            instance = new FriendServiceManager(user);
        }
        return instance;
    }

    @Override
    public void removeFriend(String toRemoveUsername) {
        User toRemove = UserFileManager.getInstance().findUserByUsername(toRemoveUsername);
        if (toRemove != null) {

            // Get the FriendManager instances for both users
            FriendManager userManager = FriendManagerFactory.getFriendManager(currentUser.getUserID());
            FriendManager toRemoveUserManager = FriendManagerFactory.getFriendManager(toRemove.getUserID());


            userManager.getFriends().remove(toRemove);
            toRemoveUserManager.getFriends().remove(currentUser);

            
//            Request request = RequestsFileManager.getInstance().searchForRequestByIds(toRemoveUsername, currentUser.getUsername());
//            if (request != null) {
//                RequestsFileManager.getInstance().getRequests().remove(request);
//
//                RequestsFileManager.getInstance().saveToFile(RequestsFileManager.getInstance().getRequests());
//                System.out.println("Request between " + currentUser.getUsername() + " and " + toRemove.getUsername() + " has been removed.");
//            }

            System.out.println(toRemove.getUsername() + " has been removed from your friend list.");

            // Save the updated friends list after modification
            UserFileManager.getInstance().saveToFile(UserFileManager.getInstance().getUsers());
            userManager.saveUserData();
            toRemoveUserManager.saveUserData();

        } else {
            System.out.println(toRemoveUsername + " not found.");
        }
    }

    @Override
    public void blockFriend(String toBlockUsername) {
        User toBlock = UserFileManager.getInstance().findUserByUsername(toBlockUsername);
        if (toBlock != null) {
            FriendManager userManager = FriendManagerFactory.getFriendManager(currentUser.getUserID());
            FriendManager toBlockUserManager = FriendManagerFactory.getFriendManager(toBlock.getUserID());

            userManager.getFriends().remove(toBlock);
            toBlockUserManager.getFriends().remove(toBlock);
            userManager.getBlocked().add(toBlock);

            System.out.println(toBlock.getUsername() + " has been blocked.");
            // Save the updated blocked list after modification
            UserFileManager.getInstance().saveToFile(UserFileManager.getInstance().getUsers());
            userManager.saveUserData();
            toBlockUserManager.saveUserData();
        } else {
            System.out.println(toBlockUsername + " not found.");
        }
    }

    public ArrayList<User> suggestFriends() {
        ArrayList<User> suggestions = new ArrayList<>();
        FriendManager userManager = FriendManagerFactory.getFriendManager(currentUser.getUserID());
        if (UserFileManager.getInstance().getUsers() != null) {
            for (User user : UserFileManager.getInstance().getUsers()) {
                if (user != this.currentUser
                        && !userManager.getFriends().contains(user)
                        && !RequestManager.getInstance(this.currentUser.getUserID()).search(user.getUserID())
                        && !userManager.getBlocked().contains(user)) {
                    suggestions.add(user);
                }
            }
        }
        return suggestions;
    }
}
